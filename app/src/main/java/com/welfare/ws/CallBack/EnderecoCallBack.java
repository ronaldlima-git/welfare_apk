package com.welfare.ws.CallBack;

import com.welfare.model.Endereco;
import com.welfare.model.Pessoa;

public interface EnderecoCallBack {
    void onSucess(Endereco endereco);
    void onFailure(Throwable t);
}
