package com.welfare.model;

import java.io.Serializable;
import java.util.Date;

public class Tratamento implements Serializable {
    private int idTratamento;
    private Date dtInicioTratamento;
    private Date dtFimTratamento;
    private Pessoa pessoa;
    private Enfermidade enfermidade;
    private Situacao situacao;
    private Contato contato;

    public Tratamento(int idTratamento, Date dtInicioTratamento, Date dtFimTratamento, Pessoa pessoa, Enfermidade enfermidade, Situacao situacao, Contato contato) {
        this.idTratamento = idTratamento;
        this.dtInicioTratamento = dtInicioTratamento;
        this.dtFimTratamento = dtFimTratamento;
        this.pessoa = pessoa;
        this.enfermidade = enfermidade;
        this.situacao = situacao;
        this.contato = contato;
    }

    public Tratamento() {
    }

    public int getIdTratamento() {
        return idTratamento;
    }

    public void setIdTratamento(int idTratamento) {
        this.idTratamento = idTratamento;
    }

    public Date getDtInicioTratamento() {
        return dtInicioTratamento;
    }

    public void setDtInicioTratamento(Date dtInicioTratamento) {
        this.dtInicioTratamento = dtInicioTratamento;
    }

    public Date getDtFimTratamento() {
        return dtFimTratamento;
    }

    public void setDtFimTratamento(Date dtFimTratamento) {
        this.dtFimTratamento = dtFimTratamento;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public Enfermidade getEnfermidade() {
        return enfermidade;
    }

    public void setEnfermidade(Enfermidade enfermidade) {
        this.enfermidade = enfermidade;
    }

    public Situacao getSituacao() {
        return situacao;
    }

    public void setSituacao(Situacao situacao) {
        this.situacao = situacao;
    }

    public Contato getContato() {
        return contato;
    }

    public void setContato(Contato contato) {
        this.contato = contato;
    }
}
