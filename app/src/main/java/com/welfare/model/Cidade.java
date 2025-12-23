package com.welfare.model;

import java.io.Serializable;

public class Cidade implements Serializable {
    private int idCidade;
    private String nomeCidade;
    private Estado estado;

    public Cidade() {
        super();
    }

    public int getIdCidade() {
        return idCidade;
    }

    public void setIdCidade(int idCidade) {
        this.idCidade = idCidade;
    }

    public String getNomeCidade() {
        return nomeCidade;
    }

    public void setNomeCidade(String nomeCidade) {
        this.nomeCidade = nomeCidade;
    }

    public Estado getEstado() {
        return estado;
    }

    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "Cidade{" +
                "idCidade=" + idCidade +
                ", nomeCidade='" + nomeCidade + '\'' +
                ", estado=" + estado +
                '}';
    }
}
