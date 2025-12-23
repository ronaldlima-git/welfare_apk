package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Cidade;
import com.welfare.ws.CallBack.CidadeCallBack;
import com.welfare.ws.Interfaces.CidadeInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class CidadeFacade {

    private CidadeInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        return retrofit.create(CidadeInterface.class);
    }

    public void find(int id, final CidadeCallBack callback){
        CidadeInterface cidadeInterface = getApi();

        Call<Cidade> callFind = cidadeInterface.find(id);

        callFind.enqueue(new Callback<Cidade>() {
            @Override
            public void onResponse(Call<Cidade> call, Response<Cidade> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }
            }

            @Override
            public void onFailure(Call<Cidade> call, Throwable t) {

            }
        });
    }

    public void insert(Cidade cidade, final CidadeCallBack callBack){
        CidadeInterface cidadeInterface = getApi();

        Call<Cidade> callInsert = cidadeInterface.insert(cidade);

        callInsert.enqueue(new Callback<Cidade>() {
            @Override
            public void onResponse(Call<Cidade> call, Response<Cidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Cidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(int idCidade, Cidade cidade, final CidadeCallBack callback){
        CidadeInterface cidadeInterface = getApi();

        Call<Cidade> callUpdate = cidadeInterface.update(idCidade, cidade);

        callUpdate.enqueue(new Callback<Cidade>() {
            @Override
            public void onResponse(Call<Cidade> call, Response<Cidade> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Cidade> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
