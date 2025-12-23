package com.welfare.ws.CallBack;

import com.welfare.model.Situacao;

import java.util.List;

public interface SituacaoCallBack {
    void onSucess(Situacao situacao);
    void onSucess(List<Situacao> situacaoList);
    void onFailure(Throwable t);
}
