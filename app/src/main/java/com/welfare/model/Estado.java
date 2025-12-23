package com.welfare.model;

import java.io.Serializable;

public class Estado implements Serializable, Comparable<Estado> {
    private Integer idEstado;
    private String ufEstado;
    private String nomeEstado;
    private Pais pais;

    public Estado(Integer idEstado, String ufEstado, String nomeEstado, Pais pais) {
        this.idEstado = idEstado;
        this.ufEstado = ufEstado;
        this.nomeEstado = nomeEstado;
        this.pais = pais;
    }

    public Estado() {
        super();
    }

    public Integer getIdEstado() {
        return idEstado;
    }

    public void setIdEstado(Integer idEstado) {
        this.idEstado = idEstado;
    }

    public String getUfEstado() {
        return ufEstado;
    }

    public void setUfEstado(String ufEstado) {
        this.ufEstado = ufEstado;
    }

    public String getNomeEstado() {
        return nomeEstado;
    }

    public void setNomeEstado(String nomeEstado) {
        this.nomeEstado = nomeEstado;
    }

    public Pais getPais() {
        return pais;
    }

    public void setPais(Pais pais) {
        this.pais = pais;
    }

    @Override
    public String toString() {
        return nomeEstado;
    }

    @Override
    public int compareTo(Estado estado) {
        return this.nomeEstado.compareTo(estado.getNomeEstado());
    }
}
