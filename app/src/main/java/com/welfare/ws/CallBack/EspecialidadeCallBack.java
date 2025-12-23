package com.welfare.ws.CallBack;

import com.welfare.model.Especialidade;

import java.util.List;

public interface EspecialidadeCallBack {
    void onSucess(Especialidade especialidade);
    void onSucess(List<Especialidade> especialidadeList);
    void onFailure(Throwable t);
}
