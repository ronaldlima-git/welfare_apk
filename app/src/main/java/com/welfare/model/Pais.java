package com.welfare.model;

import java.io.Serializable;

public class Pais implements Serializable {
    private int idPais;
    private String nomePais;

    public Pais(int idPais, String nomePais) {
        this.idPais = idPais;
        this.nomePais = nomePais;
    }

    public Pais() {
    }

    public int getIdPais() {
        return idPais;
    }

    public void setIdPais(int idPais) {
        this.idPais = idPais;
    }

    public String getNomePais() {
        return nomePais;
    }

    public void setNomePais(String nomePais) {
        this.nomePais = nomePais;
    }
}
