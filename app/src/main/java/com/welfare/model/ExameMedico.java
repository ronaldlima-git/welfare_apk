package com.welfare.model;

import android.media.Image;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class ExameMedico implements Serializable {
    private int idExame;
    private Date dtHora;
    private String nomeExame;
    private List<String> preRecomendacoes;
    private List<String> posRecomentacoes;
    private List<Image> resultExame;
    private ConsultaMedica consultaMedica;
    private Medico medicoExame;

    public ExameMedico(int idExame, Date dtHora, String nomeExame, List<String> preRecomendacoes, List<String> posRecomentacoes, List<Image> resultExame, ConsultaMedica consultaMedica, Medico medicoExame) {
        this.idExame = idExame;
        this.dtHora = dtHora;
        this.nomeExame = nomeExame;
        this.preRecomendacoes = preRecomendacoes;
        this.posRecomentacoes = posRecomentacoes;
        this.resultExame = resultExame;
        this.consultaMedica = consultaMedica;
        this.medicoExame = medicoExame;
    }

    public ExameMedico() {
    }

    public int getIdExame() {
        return idExame;
    }

    public void setIdExame(int idExame) {
        this.idExame = idExame;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public String getNomeExame() {
        return nomeExame;
    }

    public void setNomeExame(String nomeExame) {
        this.nomeExame = nomeExame;
    }

    public List<String> getPreRecomendacoes() {
        return preRecomendacoes;
    }

    public void setPreRecomendacoes(List<String> preRecomendacoes) {
        this.preRecomendacoes = preRecomendacoes;
    }

    public List<String> getPosRecomentacoes() {
        return posRecomentacoes;
    }

    public void setPosRecomentacoes(List<String> posRecomentacoes) {
        this.posRecomentacoes = posRecomentacoes;
    }

    public List<Image> getResultExame() {
        return resultExame;
    }

    public void setResultExame(List<Image> resultExame) {
        this.resultExame = resultExame;
    }

    public ConsultaMedica getConsultaMedica() {
        return consultaMedica;
    }

    public void setConsultaMedica(ConsultaMedica consultaMedica) {
        this.consultaMedica = consultaMedica;
    }

    public Medico getMedicoExame() {
        return medicoExame;
    }

    public void setMedicoExame(Medico medicoExame) {
        this.medicoExame = medicoExame;
    }
}
