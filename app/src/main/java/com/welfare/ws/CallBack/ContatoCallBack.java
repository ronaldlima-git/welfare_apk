package com.welfare.ws.CallBack;

import com.welfare.model.Contato;

import java.util.List;

public interface ContatoCallBack {
    void onSucess(Contato contato);
    void onSucess(List<Contato> contatos);
    void onFailure(Throwable t);
}
