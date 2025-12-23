package com.welfare.ws.Interfaces;

import com.welfare.model.Pessoa;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface PessoaInterface {
    //Recebe todas as pessoas da API
    @GET("pessoa")
    Call<List<Pessoa>> list();

    //Recebe as pessoas com o id informado
    @GET("pessoa/{id}")
    Call <Pessoa> find(@Path("id") int id);

    //Metodo para Realizar o Login pelo WebService
    @GET("pessoa/login")
    Call <Pessoa> login(@Query("email") String email, @Query("senha") String senha);

    @GET("pessoa/token/{token}")
    Call <Pessoa> findToken(@Path("token") String string);

    //Cria uma nova pessoa com as informações passadas
    @POST("pessoa")
    Call <Pessoa> insert(@Body Pessoa pessoa);

    //Gera um novo token para a pessoaa
    @POST("pessoa/token")
    Call <Pessoa> token(@Query("email") String email);

    //Atualiza uma pessoa com as informações passadas
    @PUT("pessoa/{id}")
    Call <Pessoa> update(@Path("id") int id, @Body Pessoa pessoa);

    //Deleta uma pessoa com as informações passadas
    @DELETE("pessoa/{id}")
    Call <Pessoa> delete(@Path("id") int id);
}
