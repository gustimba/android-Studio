package com.example.gustimba.trabalho.converter;

import com.example.gustimba.trabalho.modelo.Aluno;

import org.json.JSONException;
import org.json.JSONStringer;

import java.util.List;

/**
 * Created by Gustimba on 20/06/2017.
 */
public class AlunoConvert {

    public String convertParaJson(List<Aluno> alunos) {
        JSONStringer json = new JSONStringer();
        try {
            json.object().key("List").array().key("Alunos").array();
            for (Aluno aluno:alunos){
                json.object();
                json.key("nome").value(aluno.getNome());
                json.key("endereco").value(aluno.getEndereco());
                json.key("telefone").value(aluno.getTelefone());
                json.key("site").value(aluno.getSite());
                json.key("nota").value(aluno.getNota());
                json.endObject();
            }
            json.endArray().endObject().endArray().endObject();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return "";//json.toString();
    }
}
