package com.welfare.ws.CallBack;

import com.welfare.model.Pessoa;

import java.util.List;

public interface PessoaCallback {
    void onSucess(Pessoa pessoa);
    void onSucess(List<Pessoa> pessoas);
    void onFailure(Throwable t);
}
