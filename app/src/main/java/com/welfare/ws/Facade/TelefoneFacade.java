package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Telefone;
import com.welfare.ws.CallBack.TelefoneCallBack;
import com.welfare.ws.Interfaces.TelefoneInterface;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class TelefoneFacade {
    public List<Telefone> telefones = new ArrayList<Telefone>();

    private TelefoneInterface getApi(){

        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        return retrofit.create(TelefoneInterface.class);
    }

    public void list(final TelefoneCallBack callback){
        TelefoneInterface telefoneInterface = getApi();

        Call<List<Telefone>> callList = telefoneInterface.list();

        callList.enqueue(new Callback<List<Telefone>>() {
            @Override
            public void onResponse(Call<List<Telefone>> call, Response<List<Telefone>> response) {
                if(response.isSuccessful()){
                    telefones.addAll(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Telefone>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void find(int idTelefone, final TelefoneCallBack callback){
        TelefoneInterface telefoneInterface = getApi();

        Call<Telefone> callfind = telefoneInterface.find(idTelefone);

        callfind.enqueue(new Callback<Telefone>() {
            @Override
            public void onResponse(Call<Telefone> call, Response<Telefone> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Telefone> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void insert(Telefone telefone, final TelefoneCallBack callback){
        TelefoneInterface telefoneInterface = getApi();

        Call<Telefone> callInsert = telefoneInterface.insert(telefone);

        callInsert.enqueue(new Callback<Telefone>() {
            @Override
            public void onResponse(Call<Telefone> call, Response<Telefone> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Telefone> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void update(int idTelefone, Telefone telefone, final TelefoneCallBack callBack){
        TelefoneInterface telefoneInterface = getApi();

        Call<Telefone> callUpdate = telefoneInterface.update(idTelefone, telefone);

        callUpdate.enqueue(new Callback<Telefone>() {
            @Override
            public void onResponse(Call<Telefone> call, Response<Telefone> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Telefone> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final  TelefoneCallBack callBack){
        TelefoneInterface telefoneInterface = getApi();

        Call<Telefone> callDelete = telefoneInterface.delete(id);

        callDelete.enqueue(new Callback<Telefone>() {
            @Override
            public void onResponse(Call<Telefone> call, Response<Telefone> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Telefone> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
