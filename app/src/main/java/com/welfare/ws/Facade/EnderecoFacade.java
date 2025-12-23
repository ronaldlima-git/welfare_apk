package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Endereco;
import com.welfare.ws.CallBack.EnderecoCallBack;
import com.welfare.ws.Interfaces.EnderecoInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class EnderecoFacade {
    public List<Endereco> enderecos = new ArrayList<Endereco>();

    private EnderecoInterface getApi(){
        //Cria o conversor
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        //Retorna a interface de serviço da Classe Pessoa, com os metodos incluidos da interface
        return retrofit.create(EnderecoInterface.class);
    }

    public void list(final EnderecoCallBack callback){
        EnderecoInterface enderecoInterface = getApi();

        Call<List<Endereco>> callbackList = enderecoInterface.list();

        callbackList.enqueue(new Callback<List<Endereco>>() {
            @Override
            public void onResponse(Call<List<Endereco>> call, Response<List<Endereco>> response) {
                if(response.isSuccessful()){
                    enderecos = response.body();
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Endereco>> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void find(int id, final EnderecoCallBack callback){
        EnderecoInterface enderecoInterface = getApi();

        Call<Endereco> callFind = enderecoInterface.find(id);

        callFind.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void insert(Endereco endereco, final EnderecoCallBack callback){
        EnderecoInterface enderecoInterface = getApi();

        Call<Endereco> callInsert = enderecoInterface.insert(endereco);

        callInsert.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void update(int idEndereco, Endereco endereco, final EnderecoCallBack callback){
        EnderecoInterface enderecoInterface = getApi();

        Call<Endereco> callUpdate = enderecoInterface.update(idEndereco, endereco);

        callUpdate.enqueue(new Callback<Endereco>() {
            @Override
            public void onResponse(Call<Endereco> call, Response<Endereco> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Endereco> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }
}
