package com.example.gustimba.trabalho;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.gustimba.trabalho.Dao.AlunoDao;
import com.example.gustimba.trabalho.modelo.Aluno;

import java.io.File;

import static android.R.attr.bitmap;
import static android.R.attr.button;
import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class FormularioActivity extends AppCompatActivity {

    public static final int codigo_camera = 32;
    private FormularioHelper helper;
    private String caminhofoto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulario);
        helper = new FormularioHelper(this);
        Intent intent = getIntent();
       Aluno aluno = (Aluno) intent.getSerializableExtra("aluno");
        if (aluno != null){
            helper.preencheformulario(aluno);
        }

   Button botaofoto = (Button) findViewById(R.id.botao_foto);//Guardaremos isto em um variável, chamada
        // botaoFoto. Iremos alterar View para Button e depois, vamos importá-lo.
     botaofoto.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
          Intent intentcamera =  new Intent(MediaStore.ACTION_IMAGE_CAPTURE);   // esse classe identifica que o usriario que tirar uma foto
             caminhofoto = getExternalFilesDir(null) + "/"+ System.currentTimeMillis()+".jpg";
             //para nao apagar a imagem quando salvamos a sgunda foto temoa que colocar o metodo System.currentTimeMillis.
             //concatenamos a pasta foto,para facilitar separei o caminho da imagem em uma string
             File arquivofoto = new File(caminhofoto);
             // sempre que precisarmos especificar o caminho para ação de tirar foto mediastore.extra_output
             intentcamera.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(arquivofoto));// se temos uma intent e queremos passa outro parametro para
             // uma activity podemos usar o metodo  putExtra(). precisaremos passar um nome e um valor

             startActivityForResult(intentcamera, codigo_camera); // numero é so uma identificação so  startAtvitic
         }
     });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(resultCode == Activity.RESULT_OK){ //para poder cancelar no aplicativo da  foto
            if (requestCode == codigo_camera) {
                ImageView foto = (ImageView) findViewById(R.id.formulario_foto);
                Bitmap bitmap = BitmapFactory.decodeFile(caminhofoto);
                Bitmap bitmap_reduzir = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
                //Porém, dependo da câmera usada, a imagem pode ter uma resolução muito grande e não ser suportada pelo
                // ImageView. Teremos que reduzir o tamanho do Bitmap. Também iremos especificar as dimensões e adicionar true, para que seja usado um filtro depois da redução da imagem.
                // Para dar diferenciação, chamaremos de bitmapReduzido.
                foto.setImageBitmap(bitmap_reduzir);
                foto.setScaleType(ImageView.ScaleType.FIT_XY);
                //Com o FIT_XY, estamos especificando que a imagem deve se
                // encaixar no espaço, ajustada tanto na largura como na altura disponível no ImageView.
                foto.setTag(caminhofoto);
            }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_formulario,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_formulario_ok:
                Aluno aluno = helper.pegaluno();
                AlunoDao dao = new AlunoDao(this);
                if (aluno.getId() != null){
                    dao.alterar(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome()+" Atualizado", Toast.LENGTH_LONG).show();
                }else{
                    dao.insere(aluno);
                    Toast.makeText(FormularioActivity.this, "Aluno " +aluno.getNome()+" salvo", Toast.LENGTH_LONG).show();
                }
                dao.close();

                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
