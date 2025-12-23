package com.welfare.model;

import java.io.Serializable;

public class Medico implements Serializable {
    private int idMedico;
    private String nomeMedico;
    private Especialidade especialidade;
    private InstituicaoMedica instituicaoMedica;

    public Medico(int idMedico, String nomeMedico, Especialidade especialidade, InstituicaoMedica instituicaoMedica) {
        this.idMedico = idMedico;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.instituicaoMedica = instituicaoMedica;
    }

    public Medico() {
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public InstituicaoMedica getInstituicaoMedica() {
        return instituicaoMedica;
    }

    public void setInstituicaoMedica(InstituicaoMedica instituicaoMedica) {
        this.instituicaoMedica = instituicaoMedica;
    }
}
