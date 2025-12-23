package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Medico;
import com.welfare.ws.CallBack.MedicoCallBack;
import com.welfare.ws.Interfaces.MedicoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class MedicoFacade {

    private MedicoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(MedicoInterface.class);
    }

    public void list(final MedicoCallBack callBack){
        MedicoInterface medicoInterface = getApi();

        Call<List<Medico>> callList = medicoInterface.list();

        callList.enqueue(new Callback<List<Medico>>() {
            @Override
            public void onResponse(Call<List<Medico>> call, Response<List<Medico>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Medico>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final MedicoCallBack callBack){
        MedicoInterface medicoInterface = getApi();

        Call<Medico> callFind = medicoInterface.find(id);

        callFind.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Medico medico, final MedicoCallBack callBack){
        MedicoInterface medicoInterface = getApi();

        Call<Medico> callInsert = medicoInterface.insert(medico);

        callInsert.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Medico medico, final MedicoCallBack callBack){
        MedicoInterface medicoInterface = getApi();

        Call<Medico> callUpdate = medicoInterface.update(medico.getIdMedico(), medico);

        callUpdate.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final MedicoCallBack callBack){
        MedicoInterface medicoInterface = getApi();

        Call<Medico> callDelete = medicoInterface.delete(id);

        callDelete.enqueue(new Callback<Medico>() {
            @Override
            public void onResponse(Call<Medico> call, Response<Medico> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medico> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
