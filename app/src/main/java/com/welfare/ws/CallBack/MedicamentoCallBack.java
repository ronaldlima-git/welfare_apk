package com.welfare.ws.CallBack;

import com.welfare.model.Medicamento;

import java.util.List;

public interface MedicamentoCallBack {
    void onSucess(Medicamento medicamento);
    void onSucess(List<Medicamento> medicamentoList);
    void onFailure(Throwable t);
}
