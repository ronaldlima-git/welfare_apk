package com.welfare.model;

import java.io.Serializable;

public class Contato implements Serializable {
    private int idContato;

    private Boolean status;

    private Pessoa contato;

    private Pessoa pessoa;

    public Contato() {
    }

    public Contato(int idContato, Boolean status, Pessoa pessoaContato, Pessoa contatoPessoa) {
        this.idContato = idContato;
        this.status = status;
        this.contato = pessoaContato;
        this.pessoa = contatoPessoa;
    }

    public int getIdContato() {
        return idContato;
    }

    public void setIdContato(int idContato) {
        this.idContato = idContato;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Pessoa getContato() {
        return contato;
    }

    public void setContato(Pessoa pessoaContato) {
        this.contato = pessoaContato;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa contatoPessoa) {
        this.pessoa = contatoPessoa;
    }

    @Override
    public String toString() {
        return "Contato{" +
                "idContato=" + idContato +
                ", status=" + status +
                ", pessoaContato=" + contato +
                ", contatoPessoa=" + pessoa +
                '}';
    }
}
