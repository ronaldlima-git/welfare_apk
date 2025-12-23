package com.welfare.ws.CallBack;

import com.welfare.model.PlanoSaude;

import java.util.List;

public interface PlanoSaudeCallBack {
    void onSucess(PlanoSaude planoSaude);
    void onSucess(List<PlanoSaude> planoSaudeList);
    void onFailure(Throwable t);
}
