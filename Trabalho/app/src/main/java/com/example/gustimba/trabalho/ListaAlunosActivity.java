package com.example.gustimba.trabalho;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Browser;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import android.Manifest.permission;
import com.example.gustimba.trabalho.Dao.AlunoDao;
import com.example.gustimba.trabalho.adapter.AlunoAdapter;
import com.example.gustimba.trabalho.converter.AlunoConvert;
import com.example.gustimba.trabalho.modelo.Aluno;

import java.util.List;

import java.util.jar.Manifest;

public class ListaAlunosActivity extends AppCompatActivity {
    private ListView ListaAlunos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_alunos);

        ListaAlunos = (ListView) findViewById(R.id.lista_alunos);

        ListaAlunos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> Lista, View item, int position, long id) {
                Aluno aluno = (Aluno) ListaAlunos.getItemAtPosition(position);
                Intent intentvaiparaformulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                intentvaiparaformulario.putExtra("aluno", aluno);
                startActivity(intentvaiparaformulario);
            }
        });


        Button novoaluno = (Button) findViewById(R.id.novo_aluno);
        novoaluno.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentvaiparaformulario = new Intent(ListaAlunosActivity.this, FormularioActivity.class);
                startActivity(intentvaiparaformulario);
            }
        });
        registerForContextMenu(ListaAlunos);
    }

    private void carregaLista() {
        AlunoDao dao = new AlunoDao(this);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoAdapter adapter = new AlunoAdapter(this,alunos);
        ListaAlunos.setAdapter(adapter);
    }



    @Override
    protected void onResume() {

        super.onResume();
        carregaLista();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
            getMenuInflater().inflate(R.menu.menu_listaalunos, menu);
            return true;
        }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_enviar_notas:
               new EnviarAlunosTask(this).execute();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override    // usamos no final context para pode reutilizar esse codico para outras funçoes
    public void onCreateContextMenu(ContextMenu menu, View v, final ContextMenu.ContextMenuInfo menuInfo) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) menuInfo;
        final Aluno aluno = (Aluno) ListaAlunos.getItemAtPosition(info.position); // a palabra final é para declarar que a viariavel é constante

        MenuItem itemligar = menu.add("ligação");
        itemligar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                if (ActivityCompat.checkSelfPermission(ListaAlunosActivity.this, android.Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(ListaAlunosActivity.this,
                            new String[]{permission.CALL_PHONE}, 123);
                } else {
                    Intent intentligar = new Intent(Intent.ACTION_CALL);
                    intentligar.setData(Uri.parse("tel:" + aluno.getTelefone()));

                    startActivity(intentligar);
                }
                    return false;



            }});


        MenuItem itemsms  = menu.add("enviar sms");
        Intent intentsms = new Intent(Intent.ACTION_VIEW);
        intentsms.setData(Uri.parse("sms:" + aluno.getTelefone()));
        itemsms.setIntent(intentsms);

        MenuItem intemapa = menu.add("Visualizar mapa");
        Intent intentmapa = new Intent(Intent.ACTION_VIEW);
        intentmapa.setData(Uri.parse("geo:0,0?q="+aluno.getEndereco()));
        intemapa.setIntent(intentmapa);

        MenuItem itemsite = menu.add("visitar site");
        Intent intentsite =  new Intent(Intent.ACTION_VIEW);
        String site = aluno.getSite();
        if (!site.startsWith("http://")){
            site = "http://"+site;      // numa melhor pratica melhor assim
        }
        intentsite.setData(Uri.parse(site));
        //intentsite.setData(Uri.parse("http://"+aluno.getSite())); tinha feito assim
        itemsite.setIntent(intentsite);


        MenuItem deletar = menu.add("Deletar");
        deletar.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                AlunoDao dao = new AlunoDao(ListaAlunosActivity.this);
                dao.deleta(aluno);
                dao.close();
                Toast.makeText(ListaAlunosActivity.this, "Deletar o aluno " + aluno.getNome(), Toast.LENGTH_SHORT).show();
                carregaLista();
                return false;
            }
        });

        }


}



