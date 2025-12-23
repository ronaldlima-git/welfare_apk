package com.welfare.ws.CallBack;

import com.welfare.model.Tratamento;

import java.util.List;

public interface TratamentoCallBack {
    void onSucess(Tratamento tratamento);
    void onSucess(List<Tratamento> tratamentoList);
    void onFailure(Throwable t);
}
