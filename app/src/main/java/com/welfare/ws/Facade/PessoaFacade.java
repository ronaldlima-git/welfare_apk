package com.welfare.ws.Facade;


import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Endereco;
import com.welfare.model.Pessoa;
import com.welfare.model.Telefone;
import com.welfare.ws.CallBack.PessoaCallback;
import com.welfare.ws.Interfaces.PessoaInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;


//Usado para todas as operações do Crud Pessoa
public class PessoaFacade {

    public List<Pessoa> pessoas = new ArrayList<Pessoa>();

    //Criando o serviço do Retrofit
    private PessoaInterface getApi() {
        //Cria o conversor
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();

        //Retorna a interface de serviço da Classe Pessoa, com os metodos incluidos da interface
        return retrofit.create(PessoaInterface.class);
    }

    //Busca todas as pessoas do banco
    public void list(final PessoaCallback callback) {
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface interfacePessoa = getApi();

        //Chama o metodo list() de pessoa da InterfacePessoa
        Call<List<Pessoa>> callbackList = interfacePessoa.list();

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackList.enqueue(new Callback<List<Pessoa>>() {
            @Override
            public void onResponse(Call<List<Pessoa>> call, Response<List<Pessoa>> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }
                else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Pessoa>> call, Throwable t){
                callback.onFailure(t);
            }
        });

    }

    //Busca uma pessoa do Banco através do id passado como parametro
    public void find(int idPessoa, final  PessoaCallback callback) {
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface interfacePessoa = getApi();

        //Chama o metodo find() de pessoa da InterfacePessoa
        Call<Pessoa> callbackFind = interfacePessoa.find(idPessoa);

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackFind.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t)
            {
                callback.onFailure(t);
            }
        });

    }

    public void login(String email, String senha, final PessoaCallback callback){
        PessoaInterface interfacePessoa = getApi();

        Call<Pessoa> callLogin = interfacePessoa.login(email, senha);

        callLogin.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {

            }
        });
    }

    //Método que insere uma pesssoa no banco
    public void insert(Pessoa pessoa, final PessoaCallback callback) {
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface interfacePessoa = getApi();

        //Chama o método insert() da InterfacePessoa
        Call <Pessoa> callbackInsert = interfacePessoa.insert(pessoa);

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackInsert.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }
                else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    //Método que gera novo token a pessoa no banco
    public void token(String email, final PessoaCallback callback){
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface pessoaInterface = getApi();

        //Chama o método token() da InterfacePessoa
        Call<Pessoa> callbackToken = pessoaInterface.token(email);

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackToken.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    public void findToken(String token, final PessoaCallback callback){
        PessoaInterface pessoaInterface = getApi();

        Call<Pessoa> callbackToken = pessoaInterface.findToken(token);

        callbackToken.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()){
                    callback.onSucess(response.body());
                }else{
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    //Atualiza pessoa no Banco
    public void update(int idPessoa, Pessoa pessoa, final PessoaCallback callback){
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface interfacePessoa = getApi();

        //Chama o método update() da InterfacePessoa
        Call<Pessoa> callbackUpdate = interfacePessoa.update(idPessoa, pessoa);

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackUpdate.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }
                else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Pessoa> call, Throwable t) {
                callback.onFailure(t);
            }
        });
    }

    //Deleta uma pessoa do Banco
    public void delete(int idPessoa, final PessoaCallback callback) {
        //Cria a comunicação e atribui todas as operações da Interface para o objeto interfacePessoa
        PessoaInterface interfacePessoa = getApi();

        //Chama o método delete() da InterfacePessoa
        Call<Pessoa> callbackdelete = interfacePessoa.delete(idPessoa);

        //Realiza o metodo assíncrono utilizando retrofit, e sobrescrevendo os metodos da interface PessoaCallback
        callbackdelete.enqueue(new Callback<Pessoa>() {
            @Override
            public void onResponse(Call<Pessoa> call, Response<Pessoa> response) {
                if(response.isSuccessful()) {
                    callback.onSucess(response.body());
                }
                else {
                    callback.onFailure(new Exception(response.errorBody().toString()));
                }
            }
            @Override
            public void onFailure(Call<Pessoa> call, Throwable t){
                callback.onFailure(t);
            }
        });
    }
}