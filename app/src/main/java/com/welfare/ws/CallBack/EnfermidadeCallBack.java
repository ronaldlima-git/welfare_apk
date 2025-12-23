package com.welfare.ws.CallBack;

import com.welfare.model.Enfermidade;

import java.util.List;

public interface EnfermidadeCallBack {
    void onSucess(Enfermidade enfermidade);
    void onSucess(List<Enfermidade> enfermidadeList);
    void onFailure(Throwable t);
}
