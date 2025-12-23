package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Situacao;
import com.welfare.ws.CallBack.SituacaoCallBack;
import com.welfare.ws.Interfaces.SituacaoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class SituacaoFacade {
    private SituacaoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(SituacaoInterface.class);
    }

    public void list(final SituacaoCallBack callBack){
        SituacaoInterface situacaoInterface = getApi();

        Call<List<Situacao>> callList = situacaoInterface.list();

        callList.enqueue(new Callback<List<Situacao>>() {
            @Override
            public void onResponse(Call<List<Situacao>> call, Response<List<Situacao>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Situacao>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final SituacaoCallBack callBack){
        SituacaoInterface situacaoInterface = getApi();

        Call<Situacao> callFind = situacaoInterface.find(id);

        callFind.enqueue(new Callback<Situacao>() {
            @Override
            public void onResponse(Call<Situacao> call, Response<Situacao> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Situacao> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Situacao situacao, final SituacaoCallBack callBack){
        SituacaoInterface situacaoInterface = getApi();

        Call<Situacao> callInsert = situacaoInterface.insert(situacao);

        callInsert.enqueue(new Callback<Situacao>() {
            @Override
            public void onResponse(Call<Situacao> call, Response<Situacao> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Situacao> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Situacao situacao, final SituacaoCallBack callBack){
        SituacaoInterface situacaoInterface = getApi();

        Call<Situacao> callUpdate = situacaoInterface.update(situacao.getIdSituacao(), situacao);

        callUpdate.enqueue(new Callback<Situacao>() {
            @Override
            public void onResponse(Call<Situacao> call, Response<Situacao> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Situacao> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final SituacaoCallBack callBack){
        SituacaoInterface situacaoInterface = getApi();

        Call<Situacao> callDelete = situacaoInterface.delete(id);

        callDelete.enqueue(new Callback<Situacao>() {
            @Override
            public void onResponse(Call<Situacao> call, Response<Situacao> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Situacao> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
