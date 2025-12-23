package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.InstituicaoMedica;
import com.welfare.ws.CallBack.InstituicaoMedicaCallBack;
import com.welfare.ws.Interfaces.InstituicaoMedicaInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class InstituicaoFacade {
    private InstituicaoMedicaInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(InstituicaoMedicaInterface.class);
    }

    public void list(final InstituicaoMedicaCallBack callBack){
        InstituicaoMedicaInterface instituicaoInterface = getApi();

        Call<List<InstituicaoMedica>> callList = instituicaoInterface.list();

        callList.enqueue(new Callback<List<InstituicaoMedica>>() {
            @Override
            public void onResponse(Call<List<InstituicaoMedica>> call, Response<List<InstituicaoMedica>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<InstituicaoMedica>> call, Throwable t) {

            }
        });
    }

    public void find(int id, final InstituicaoMedicaCallBack callBack){
        InstituicaoMedicaInterface instituicaoInterface = getApi();

        Call<InstituicaoMedica> callFind = instituicaoInterface.find(id);

        callFind.enqueue(new Callback<InstituicaoMedica>() {
            @Override
            public void onResponse(Call<InstituicaoMedica> call, Response<InstituicaoMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<InstituicaoMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(InstituicaoMedica instituicaoMedica, final InstituicaoMedicaCallBack callBack){
        InstituicaoMedicaInterface instituicaoInterface = getApi();

        Call<InstituicaoMedica> callInsert = instituicaoInterface.insert(instituicaoMedica);

        callInsert.enqueue(new Callback<InstituicaoMedica>() {
            @Override
            public void onResponse(Call<InstituicaoMedica> call, Response<InstituicaoMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<InstituicaoMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(InstituicaoMedica instituicaoMedica, final InstituicaoMedicaCallBack callBack){
        InstituicaoMedicaInterface instituicaoInterface = getApi();

        Call<InstituicaoMedica> callUpdate = instituicaoInterface.update(instituicaoMedica.getIdInstituicao(), instituicaoMedica);

        callUpdate.enqueue(new Callback<InstituicaoMedica>() {
            @Override
            public void onResponse(Call<InstituicaoMedica> call, Response<InstituicaoMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<InstituicaoMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final InstituicaoMedicaCallBack callBack){
        InstituicaoMedicaInterface instituicaoInterface = getApi();

        Call<InstituicaoMedica> callDelete = instituicaoInterface.delete(id);

        callDelete.enqueue(new Callback<InstituicaoMedica>() {
            @Override
            public void onResponse(Call<InstituicaoMedica> call, Response<InstituicaoMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<InstituicaoMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
