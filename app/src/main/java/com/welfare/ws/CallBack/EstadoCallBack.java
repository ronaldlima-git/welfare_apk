package com.welfare.ws.CallBack;

import com.welfare.model.Estado;

import java.util.List;

public interface EstadoCallBack {
    void onSucess(Estado estado);
    void onSucess(List<Estado> estadoList);
    void onFailure(Throwable t);
}
