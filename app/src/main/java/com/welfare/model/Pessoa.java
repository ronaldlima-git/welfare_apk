package com.welfare.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Pessoa implements Serializable
{
    //Atributos para vincular a resposta Json ao Objeto
    private int idPessoa;
    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private Date dtNascimento;
    private String token;
    private Telefone telefone;
    private Endereco endereco;

    public Pessoa() {
    }

    public Pessoa(int idPessoa, String nome, String email, String senha, String cpf, Date dtNascimento, String token, Telefone telefone, Endereco endereco) {
    this.idPessoa = idPessoa;
    this.nome = nome;
    this.email = email;
    this.senha = senha;
    this.cpf = cpf;
    this.dtNascimento = dtNascimento;
    this.token = token;
    this.telefone = telefone;
    this.endereco = endereco;
}

    public int getIdPessoa() {
        return idPessoa;
    }

    public void setIdPessoa(int idPessoa) {
        this.idPessoa = idPessoa;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public Date getDtNascimento() {
        return dtNascimento;
    }

    public void setDtNascimento(Date dtNascimento) {
        this.dtNascimento = dtNascimento;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Telefone getTelefone() {
        return this.telefone;
    }

    public void setTelefone(Telefone telefone) {
        this.telefone = telefone;
    }

    public Endereco getEndereco() {
        return this.endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "idPessoa=" + idPessoa +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", cpf='" + cpf + '\'' +
                ", dtNascimento=" + dtNascimento +
                ", token='" + token + '\'' +
                ", Telefone=" + telefone +
                ", Endereco=" + endereco +
                '}';
    }
}
