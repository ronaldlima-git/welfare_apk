package com.welfare.ws.CallBack;

import com.welfare.model.ConsultaMedica;

import java.util.List;

public interface ConsultaMedicaCallBack {
    void onSucess(ConsultaMedica consultaMedica);
    void onSucess(List<ConsultaMedica> consultaMedicas);
    void onFailure(Throwable t);
}
