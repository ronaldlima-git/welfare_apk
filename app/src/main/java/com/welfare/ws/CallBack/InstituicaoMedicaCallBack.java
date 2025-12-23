package com.welfare.ws.CallBack;

import com.welfare.model.InstituicaoMedica;

import java.util.List;

public interface InstituicaoMedicaCallBack {
    void onSucess(InstituicaoMedica instituicaoMedica);
    void onSucess(List<InstituicaoMedica> instituicaoMedicaList);
    void onFailure(Throwable t);
}