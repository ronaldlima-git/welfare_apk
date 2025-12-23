package com.welfare.model;

import java.io.Serializable;
import java.util.List;

public class Enfermidade implements Serializable {
    private int idEnfermidade;
    private String nomeEnfermidade;
    private List<String> efeitosConhecidos;

    public Enfermidade(int idEnfermidade, String nomeEnfermidade, List<String> efeitosConhecidos) {
        this.idEnfermidade = idEnfermidade;
        this.nomeEnfermidade = nomeEnfermidade;
        this.efeitosConhecidos = efeitosConhecidos;
    }

    public Enfermidade() {
    }

    public int getIdEnfermidade() {
        return idEnfermidade;
    }

    public void setIdEnfermidade(int idEnfermidade) {
        this.idEnfermidade = idEnfermidade;
    }

    public String getNomeEnfermidade() {
        return nomeEnfermidade;
    }

    public void setNomeEnfermidade(String nomeEnfermidade) {
        this.nomeEnfermidade = nomeEnfermidade;
    }

    public List<String> getEfeitosConhecidos() {
        return efeitosConhecidos;
    }

    public void setEfeitosConhecidos(List<String> efeitosConhecidos) {
        this.efeitosConhecidos = efeitosConhecidos;
    }
}
