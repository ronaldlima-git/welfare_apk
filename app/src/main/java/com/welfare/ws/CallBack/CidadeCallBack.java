package com.welfare.ws.CallBack;

import com.welfare.model.Cidade;

public interface CidadeCallBack {
    void onSucess(Cidade cidade);
    void onFailure(Throwable t);
}
