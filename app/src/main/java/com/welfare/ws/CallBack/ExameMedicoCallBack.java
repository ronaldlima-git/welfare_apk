package com.welfare.ws.CallBack;

import com.welfare.model.ExameMedico;
import com.welfare.ws.Interfaces.ExameMedicoInterface;

import java.util.List;

public interface ExameMedicoCallBack {
    void onSucess(ExameMedico exameMedico);
    void onSucess(List<ExameMedico> exameMedicoList);
    void onFailure(Throwable t);
}
