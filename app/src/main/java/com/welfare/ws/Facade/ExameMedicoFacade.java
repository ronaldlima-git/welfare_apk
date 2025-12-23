package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.ExameMedico;
import com.welfare.ws.CallBack.ExameMedicoCallBack;
import com.welfare.ws.Interfaces.ExameMedicoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class ExameMedicoFacade {
    private ExameMedicoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        return retrofit.create(ExameMedicoInterface.class);
    }

    public void list(final ExameMedicoCallBack callBack){
        ExameMedicoInterface exameInterface = getApi();

        Call<List<ExameMedico>> callList = exameInterface.list();

        callList.enqueue(new Callback<List<ExameMedico>>() {
            @Override
            public void onResponse(Call<List<ExameMedico>> call, Response<List<ExameMedico>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<ExameMedico>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final ExameMedicoCallBack callBack){
        ExameMedicoInterface exameInterface = getApi();

        Call<ExameMedico> callFind = exameInterface.find(id);

        callFind.enqueue(new Callback<ExameMedico>() {
            @Override
            public void onResponse(Call<ExameMedico> call, Response<ExameMedico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ExameMedico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(ExameMedico exameMedico, final ExameMedicoCallBack callBack){
        ExameMedicoInterface exameInterface = getApi();

        Call<ExameMedico> callInsert = exameInterface.insert(exameMedico);

        callInsert.enqueue(new Callback<ExameMedico>() {
            @Override
            public void onResponse(Call<ExameMedico> call, Response<ExameMedico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ExameMedico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(ExameMedico exameMedico, final ExameMedicoCallBack callBack){
        ExameMedicoInterface exameInterface = getApi();

        Call<ExameMedico> callUpdate = exameInterface.update(exameMedico.getIdExame(), exameMedico);

        callUpdate.enqueue(new Callback<ExameMedico>() {
            @Override
            public void onResponse(Call<ExameMedico> call, Response<ExameMedico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ExameMedico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final ExameMedicoCallBack callBack){
        ExameMedicoInterface exameInterface = getApi();

        Call<ExameMedico> callDelete = exameInterface.delete(id);

        callDelete.enqueue(new Callback<ExameMedico>() {
            @Override
            public void onResponse(Call<ExameMedico> call, Response<ExameMedico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ExameMedico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
