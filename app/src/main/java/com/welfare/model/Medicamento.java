package com.welfare.model;

import java.io.Serializable;
import java.util.List;

public class Medicamento implements Serializable {
    private int idMedicamento;
    private String nomeMedicamento;
    private int tempoUso;
    private int periodicidadeHoras;
    private List<String> efeitosColaterais;
    private Enfermidade efermidade;

    public Medicamento(int idMedicamento, String nomeMedicamento, int tempoUso, int periodicidadeHoras, List<String> efeitosColaterais, Enfermidade efermidade) {
        this.idMedicamento = idMedicamento;
        this.nomeMedicamento = nomeMedicamento;
        this.tempoUso = tempoUso;
        this.periodicidadeHoras = periodicidadeHoras;
        this.efeitosColaterais = efeitosColaterais;
        this.efermidade = efermidade;
    }

    public Medicamento() {
    }

    public int getIdMedicamento() {
        return idMedicamento;
    }

    public void setIdMedicamento(int idMedicamento) {
        this.idMedicamento = idMedicamento;
    }

    public String getNomeMedicamento() {
        return nomeMedicamento;
    }

    public void setNomeMedicamento(String nomeMedicamento) {
        this.nomeMedicamento = nomeMedicamento;
    }

    public int getTempoUso() {
        return tempoUso;
    }

    public void setTempoUso(int tempoUso) {
        this.tempoUso = tempoUso;
    }

    public int getPeriodicidadeHoras() {
        return periodicidadeHoras;
    }

    public void setPeriodicidadeHoras(int periodicidadeHoras) {
        this.periodicidadeHoras = periodicidadeHoras;
    }

    public List<String> getEfeitosColaterais() {
        return efeitosColaterais;
    }

    public void setEfeitosColaterais(List<String> efeitosColaterais) {
        this.efeitosColaterais = efeitosColaterais;
    }

    public Enfermidade getEfermidade() {
        return efermidade;
    }

    public void setEfermidade(Enfermidade efermidade) {
        this.efermidade = efermidade;
    }
}
