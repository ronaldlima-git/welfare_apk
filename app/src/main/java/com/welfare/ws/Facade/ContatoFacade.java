package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Contato;
import com.welfare.ws.CallBack.ContatoCallBack;
import com.welfare.ws.Interfaces.ContatoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class ContatoFacade {


    private ContatoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(ContatoInterface.class);
    }


    public void list(final ContatoCallBack callBack){
        ContatoInterface interfaceContato = getApi();

        Call<List<Contato>> callbackList = interfaceContato.list();

        callbackList.enqueue(new Callback<List<Contato>>() {
            @Override
            public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Contato>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final ContatoCallBack callBack){
        ContatoInterface interfaceContato = getApi();

        Call<Contato> callbackFind = interfaceContato.find(id);

        callbackFind.enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void findByContatoPessoa(int idPessoa, final ContatoCallBack callBack){
        ContatoInterface interfaceContato = getApi();

        Call<List<Contato>> callbackContatoPessoa = interfaceContato.findByContatoPessoa(idPessoa);

        callbackContatoPessoa.enqueue(new Callback<List<Contato>>() {
            @Override
            public void onResponse(Call<List<Contato>> call, Response<List<Contato>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Contato>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });

    }

    public void insert(Contato contato, final ContatoCallBack callBack) {
        ContatoInterface interfaceContato = getApi();

        Call<Contato> callbackInsert = interfaceContato.insert(contato);

        callbackInsert.enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(int id, Contato contato, final ContatoCallBack callBack){
        ContatoInterface interfaceContato = getApi();

        Call<Contato> callbackUpdate = interfaceContato.update(id, contato);

        callbackUpdate.enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final ContatoCallBack callBack){
        ContatoInterface interfaceContato = getApi();

        Call<Contato> callbackDelete = interfaceContato.delete(id);

        callbackDelete.enqueue(new Callback<Contato>() {
            @Override
            public void onResponse(Call<Contato> call, Response<Contato> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Contato> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
