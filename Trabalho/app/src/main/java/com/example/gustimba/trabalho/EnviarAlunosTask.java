package com.example.gustimba.trabalho;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.view.View;
import android.widget.Toast;

import com.example.gustimba.trabalho.Dao.AlunoDao;
import com.example.gustimba.trabalho.converter.AlunoConvert;
import com.example.gustimba.trabalho.modelo.Aluno;

import java.io.IOException;
import java.util.List;

/**
 * Created by Gustimba on 21/06/2017.
 */

public class EnviarAlunosTask extends AsyncTask<Void,Void,String> {
    private Context context;
    private ProgressDialog dialog;

    public EnviarAlunosTask(Context context) {
        this.context = context;
    }

    @Override
    protected void onPreExecute() {
         dialog = ProgressDialog.show(context,"Aguarde","enviando alunos .. ", true, true);
    }

    @Override
    protected String doInBackground(Void... params) {
        AlunoDao dao = new AlunoDao(context);
        List<Aluno> alunos = dao.buscaAlunos();
        dao.close();

        AlunoConvert converor  = new AlunoConvert();
        String json = converor.convertParaJson(alunos);

        WebCliente Client = new WebCliente();
        String resposta = null;
        try {
            resposta = Client.post(json);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return resposta;
    }

    @Override
    protected void onPostExecute(String resposta) {
        dialog.dismiss();
        Toast.makeText(context,resposta, Toast.LENGTH_SHORT).show();

    }
}
