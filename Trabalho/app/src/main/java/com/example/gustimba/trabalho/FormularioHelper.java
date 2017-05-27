package com.example.gustimba.trabalho;

import android.widget.EditText;
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
    private Aluno aluno;

    public FormularioHelper(FormularioActivity activity){
        campoNome = (EditText) activity.findViewById(R.id.formulario_nome);
        campoEndereco = (EditText) activity.findViewById(R.id.formulario_endereco);
        campoTelefone = (EditText) activity.findViewById(R.id.formulario_telefone);
        campoSite = (EditText) activity.findViewById(R.id.formulario_site);
        camponota = (RatingBar) activity.findViewById(R.id.formulario_estrelas);
        aluno = new Aluno();
    }

    public Aluno pegaluno() {

        aluno.setNome(campoNome.getText().toString());
        aluno.setEndereco(campoEndereco.getText().toString());
        aluno.setTelefone(campoTelefone.getText().toString());
        aluno.setSite(campoSite.getText().toString());
        aluno.setNota(Double.valueOf(camponota.getProgress()));
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