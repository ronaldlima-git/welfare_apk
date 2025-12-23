package com.welfare.model;

import java.io.Serializable;
import java.util.Date;

public class PlanoSaude implements Serializable {
    private int idPlano;
    private String nomePlano;
    private String nomePrestadora;
    private String cobertura;
    private Date dtContratacao;
    private Double valorMensal;
    private String situacao;
    private InstituicaoMedica instituicaoMedica;
    private Pessoa pessoa;

    public PlanoSaude(int idPlano, String nomePlano, String nomePrestadora, String cobertura, Date dtContratacao, Double valorMensal, String situacao, InstituicaoMedica instituicaoMedica, Pessoa pessoa) {
        this.idPlano = idPlano;
        this.nomePlano = nomePlano;
        this.nomePrestadora = nomePrestadora;
        this.cobertura = cobertura;
        this.dtContratacao = dtContratacao;
        this.valorMensal = valorMensal;
        this.situacao = situacao;
        this.instituicaoMedica = instituicaoMedica;
        this.pessoa = pessoa;
    }

    public PlanoSaude() {
    }

    public int getIdPlano() {
        return idPlano;
    }

    public void setIdPlano(int idPlano) {
        this.idPlano = idPlano;
    }

    public String getNomePlano() {
        return nomePlano;
    }

    public void setNomePlano(String nomePlano) {
        this.nomePlano = nomePlano;
    }

    public String getNomePrestadora() {
        return nomePrestadora;
    }

    public void setNomePrestadora(String nomePrestadora) {
        this.nomePrestadora = nomePrestadora;
    }

    public String getCobertura() {
        return cobertura;
    }

    public void setCobertura(String cobertura) {
        this.cobertura = cobertura;
    }

    public Date getDtContratacao() {
        return dtContratacao;
    }

    public void setDtContratacao(Date dtContratacao) {
        this.dtContratacao = dtContratacao;
    }

    public Double getValorMensal() {
        return valorMensal;
    }

    public void setValorMensal(Double valorMensal) {
        this.valorMensal = valorMensal;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public InstituicaoMedica getInstituicaoMedica() {
        return instituicaoMedica;
    }

    public void setInstituicaoMedica(InstituicaoMedica instituicaoMedica) {
        this.instituicaoMedica = instituicaoMedica;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }
}
