package com.welfare.model;

import java.io.Serializable;

public class Telefone implements Serializable {
    private int idTelefone;
    private String numero;

    public Telefone(int idTelefone, String numero) {
        this.idTelefone = idTelefone;
        this.numero = numero;
    }

    public Telefone() {
    }

    public int getIdTelefone() {
        return idTelefone;
    }

    public void setIdTelefone(int idTelefone) {
        this.idTelefone = idTelefone;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Telefone{" +
                "idTelefone=" + idTelefone +
                ", numero='" + numero + '\'' +
                '}';
    }
}
