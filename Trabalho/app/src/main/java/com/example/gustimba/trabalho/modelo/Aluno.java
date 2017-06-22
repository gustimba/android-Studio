package com.example.gustimba.trabalho.modelo;


import android.widget.ImageView;

import java.io.Serializable;

public class Aluno implements Serializable {//para converter em  binario, consequindo  assim passar para outra activity.
    private  Long id;
    private String nome;
    private  String endereco;
    private  String telefone;
    private  String site;
    private  Double nota;
    private  String caminhFoto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getCaminhoFoto(){return caminhFoto;}

    public void setCaminhoFoto(String caminhFoto){this.caminhFoto = caminhFoto;}

    @Override
    public  String toString(){
        return  getId() +" - "+getNome();
    }
}
