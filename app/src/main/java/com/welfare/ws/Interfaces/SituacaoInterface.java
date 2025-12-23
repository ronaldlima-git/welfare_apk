package com.welfare.ws.Interfaces;

import com.welfare.model.Situacao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface SituacaoInterface {
    @GET("situacao")
    Call<List<Situacao>> list();

    @GET("situacao/{id}")
    Call<Situacao> find(@Path("id") int id);

    @POST("situacao")
    Call<Situacao> insert(@Body Situacao situacao);

    @PUT("situacao/{id}")
    Call<Situacao> update(@Path("id") int id, @Body Situacao situacao);

    @DELETE("situacao/{id}")
    Call<Situacao> delete(@Path("id") int id);
}
