package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Estado;
import com.welfare.ws.CallBack.EstadoCallBack;
import com.welfare.ws.Interfaces.EstadoInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class EstadoFacade {
    public List<Estado> estados = new ArrayList<Estado>();

    private EstadoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        return retrofit.create(EstadoInterface.class);
    }

    public void list(final EstadoCallBack callback){
        EstadoInterface estadoInterface = getApi();

        Call<List<Estado>> callList = estadoInterface.list();

        callList.enqueue(new Callback<List<Estado>>() {
            @Override
            public void onResponse(Call<List<Estado>> call, Response<List<Estado>> response) {
                if(response.isSuccessful()){
                    estados = response.body();
                    callback.onSucess(estados);
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Estado>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void find(int id, final EstadoCallBack callback){
        EstadoInterface estadoInterface = getApi();

        Call<Estado> callFind = estadoInterface.find(id);

        callFind.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void update(int id, Estado estado, final EstadoCallBack callback){
        EstadoInterface estadoInterface = getApi();

        Call<Estado> callUpdate = estadoInterface.update(id, estado);

        callUpdate.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void delete(int id, final EstadoCallBack callBack){
        EstadoInterface estadoInterface = getApi();

        Call<Estado> callDelete = estadoInterface.delete(id);

        callDelete.enqueue(new Callback<Estado>() {
            @Override
            public void onResponse(Call<Estado> call, Response<Estado> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Estado> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
