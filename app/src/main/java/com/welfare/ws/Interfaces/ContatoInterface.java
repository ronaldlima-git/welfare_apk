package com.welfare.ws.Interfaces;

import com.welfare.model.Contato;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ContatoInterface {
    @GET("contato")
    Call<List<Contato>> list();

    @GET("contato/{id}")
    Call<Contato> find(@Path("id") int id);

    @GET("/contato/pessoa/{idPessoa}")
    Call<List<Contato>> findByContatoPessoa(@Path("idPessoa") int idPessoa);

    @POST("contato")
    Call<Contato> insert(@Body Contato contato);

    @PUT("contato/{id}")
    Call<Contato> update(@Path("id") int id, @Body Contato contato);

    @DELETE("contato/{id}")
    Call<Contato> delete(@Path("id") int id);
}
