package com.example.gustimba.trabalho;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;

import com.example.gustimba.trabalho.modelo.Aluno;
import com.example.gustimba.trabalho.R;
import com.example.gustimba.trabalho.FormularioActivity;

/**
 * Created by Gustimba on 09/04/2017.
 */

public class FormularioHelper {

    private final EditText campoNome;
    private final EditText campoEndereco;
    private final EditText campoTelefone;
    private final EditText campoSite;
    private final RatingBar camponota;
    private  final ImageView campoFoto;
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        camponota = (RatingBar) activity.findViewById(R.id.formulario_estrelas);
        campoFoto = (ImageView) activity.findViewById(R.id.formulario_foto);
        aluno = new Aluno();

    }
     public void carregaimagem (String caminhofoto){
        if(caminhofoto != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(caminhofoto);
            Bitmap bitmap_reduzir = Bitmap.createScaledBitmap(bitmap, 300, 300, true);
            //Porém, dependo da câmera usada, a imagem pode ter uma resolução muito grande e não ser suportada pelo
            // ImageView. Teremos que reduzir o tamanho do Bitmap. Também iremos especificar as dimensões e adicionar true, para que seja usado um filtro depois da redução da imagem.
            // Para dar diferenciação, chamaremos de bitmapReduzido.
            campoFoto.setImageBitmap(bitmap_reduzir);
            campoFoto.setScaleType(ImageView.ScaleType.FIT_XY);
            //Com o FIT_XY, estamos especificando que a imagem deve se
            // encaixar no espaço, ajustada tanto na largura como na altura disponível no ImageView.
            campoFoto.setTag(caminhofoto);
             }
        }


    public Aluno pegaluno() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(camponota.getProgress()));
        aluno.setCaminhoFoto((String) campoFoto.getTag());
        return  aluno;
    }

    public void preencheformulario(Aluno aluno) {
        campoNome.setText(aluno.getNome());
        campoEndereco.setText(aluno.getEndereco());
        campoTelefone.setText(aluno.getTelefone());
        campoSite.setText(aluno.getSite());
        camponota.setProgress(aluno.getNota().intValue());
        this.aluno = aluno;// instanciando  para pegar o id
    }
}