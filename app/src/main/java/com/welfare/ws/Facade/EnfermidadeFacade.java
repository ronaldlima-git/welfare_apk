package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Enfermidade;
import com.welfare.ws.CallBack.EnfermidadeCallBack;
import com.welfare.ws.Interfaces.EnfermidadeInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class EnfermidadeFacade {
    private EnfermidadeInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        return retrofit.create(EnfermidadeInterface.class);
    }

    public void list(final EnfermidadeCallBack callBack){
        EnfermidadeInterface enfermidadeInterface = getApi();

        Call<List<Enfermidade>> callList = enfermidadeInterface.list();

        callList.enqueue(new Callback<List<Enfermidade>>() {
            @Override
            public void onResponse(Call<List<Enfermidade>> call, Response<List<Enfermidade>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Enfermidade>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final EnfermidadeCallBack callBack){
        EnfermidadeInterface enfermidadeInterface = getApi();

        Call<Enfermidade> callFind = enfermidadeInterface.find(id);

        callFind.enqueue(new Callback<Enfermidade>() {
            @Override
            public void onResponse(Call<Enfermidade> call, Response<Enfermidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Enfermidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Enfermidade enfermidade, final EnfermidadeCallBack callBack){
        EnfermidadeInterface enfermidadeInterface = getApi();

        Call<Enfermidade> callInsert = enfermidadeInterface.insert(enfermidade);

        callInsert.enqueue(new Callback<Enfermidade>() {
            @Override
            public void onResponse(Call<Enfermidade> call, Response<Enfermidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else {
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Enfermidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Enfermidade enfermidade, final EnfermidadeCallBack callBack){
        EnfermidadeInterface enfermidadeInterface = getApi();

        Call<Enfermidade> callUpdade = enfermidadeInterface.update(enfermidade.getIdEnfermidade(), enfermidade);

        callUpdade.enqueue(new Callback<Enfermidade>() {
            @Override
            public void onResponse(Call<Enfermidade> call, Response<Enfermidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Enfermidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final EnfermidadeCallBack callBack){
        EnfermidadeInterface enfermidadeInterface = getApi();

        Call<Enfermidade> callDelete = enfermidadeInterface.delete(id);

        callDelete.enqueue(new Callback<Enfermidade>() {
            @Override
            public void onResponse(Call<Enfermidade> call, Response<Enfermidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Enfermidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
