package com.welfare.model;

import java.io.Serializable;
import java.util.Date;

public class ConsultaMedica implements Serializable {
    private int idConsulta;
    private Date dtHora;
    private Date dtRetorno;
    private Medico medicoConsulta;
    private Pessoa pessoa;
    private PlanoSaude planoSaude;
    private Contato contatoConsulta;

    public ConsultaMedica(int idConsulta, Date dtHora, Date dtRetorno, Medico medicoConsulta, Pessoa pessoa, PlanoSaude planoSaude, Contato contatoConsulta) {
        this.idConsulta = idConsulta;
        this.dtHora = dtHora;
        this.dtRetorno = dtRetorno;
        this.medicoConsulta = medicoConsulta;
        this.pessoa = pessoa;
        this.planoSaude = planoSaude;
        this.contatoConsulta = contatoConsulta;
    }

    public ConsultaMedica() {
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public Date getDtHora() {
        return dtHora;
    }

    public void setDtHora(Date dtHora) {
        this.dtHora = dtHora;
    }

    public Date getDtRetorno() {
        return dtRetorno;
    }

    public void setDtRetorno(Date dtRetorno) {
        this.dtRetorno = dtRetorno;
    }

    public Medico getMedicoConsulta() {
        return medicoConsulta;
    }

    public void setMedicoConsulta(Medico medicoConsulta) {
        this.medicoConsulta = medicoConsulta;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public PlanoSaude getPlanoSaude() {
        return planoSaude;
    }

    public void setPlanoSaude(PlanoSaude planoSaude) {
        this.planoSaude = planoSaude;
    }

    public Contato getContatoConsulta() {
        return contatoConsulta;
    }

    public void setContatoConsulta(Contato contatoConsulta) {
        this.contatoConsulta = contatoConsulta;
    }
}
