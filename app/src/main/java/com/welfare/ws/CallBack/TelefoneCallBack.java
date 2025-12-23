package com.welfare.ws.CallBack;

import com.welfare.model.Telefone;

import java.util.List;

public interface TelefoneCallBack {
    void onSucess(Telefone telefone);
    void onFailure(Throwable t);
}
