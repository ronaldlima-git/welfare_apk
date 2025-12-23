package com.welfare.model;

import java.io.Serializable;

public class Endereco implements Serializable {
    //Atributos
    private int idEndereco;

    private String cep;

    private String bairro;

    private String rua;

    private int numRua;

    private String complemento;

    private Cidade cidade;

    public Endereco() {
    }

    public Endereco(int idEndereco, String cep, String bairro, String rua, int numRua, String complemento, Cidade cidade) {
        this.idEndereco = idEndereco;
        this.cep = cep;
        this.bairro = bairro;
        this.rua = rua;
        this.numRua = numRua;
        this.complemento = complemento;
        this.cidade = cidade;
    }

    public int getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(int idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumRua() {
        return numRua;
    }

    public void setNumRua(int numRua) {
        this.numRua = numRua;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public Cidade getCidade() {
        return cidade;
    }

    public void setCidade(Cidade cidade) {
        this.cidade = cidade;
    }

    @Override
    public String toString() {
        return "Endereco{" +
                "idEndereco=" + idEndereco +
                ", cep='" + cep + '\'' +
                ", bairro='" + bairro + '\'' +
                ", rua='" + rua + '\'' +
                ", numRua=" + numRua +
                ", complemento='" + complemento + '\'' +
                ", cidade=" + cidade +
                '}';
    }
}
