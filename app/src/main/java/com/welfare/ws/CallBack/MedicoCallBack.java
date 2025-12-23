package com.welfare.ws.CallBack;

import com.welfare.model.Medico;

import java.util.List;

public interface MedicoCallBack {
    void onSucess(Medico medico);
    void onSucess(List<Medico> medicos);
    void onFailure(Throwable t);
}
