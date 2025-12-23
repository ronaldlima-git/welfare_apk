package com.welfare.model;

import java.io.Serializable;

public class Situacao implements Serializable {
    private int idSituacao;
    private String situacao;

    public Situacao(int idSituacao, String situacao) {
        this.idSituacao = idSituacao;
        this.situacao = situacao;
    }

    public Situacao() {
    }

    public int getIdSituacao() {
        return idSituacao;
    }

    public void setIdSituacao(int idSituacao) {
        this.idSituacao = idSituacao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
}
