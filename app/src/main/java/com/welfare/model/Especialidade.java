package com.welfare.model;

import java.io.Serializable;

public class Especialidade implements Serializable {
    private int idEspecialidade;
    private String nomeEspecialidade;

    public Especialidade(int idEspecialidade, String nomeEspecialidade) {
        this.idEspecialidade = idEspecialidade;
        this.nomeEspecialidade = nomeEspecialidade;
    }

    public Especialidade() {
    }

    public int getIdEspecialidade() {
        return idEspecialidade;
    }

    public void setIdEspecialidade(int idEspecialidade) {
        this.idEspecialidade = idEspecialidade;
    }

    public String getNomeEspecialidade() {
        return nomeEspecialidade;
    }

    public void setNomeEspecialidade(String nomeEspecialidade) {
        this.nomeEspecialidade = nomeEspecialidade;
    }
}
