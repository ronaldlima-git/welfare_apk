package com.welfare.ws.Interfaces;

import com.welfare.model.Cidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CidadeInterface {
    @GET("cidade")
    Call<List<Cidade>> list();

    @GET("cidade/{id}")
    Call<Cidade> find(@Path("id") int id);

    @POST("cidade")
    Call<Cidade> insert(@Body Cidade cidade);

    @PUT("cidade/{id}")
    Call<Cidade> update(@Path("id") int id, @Body Cidade cidade);

    @DELETE("cidade/{id}")
    Call<Cidade> delete(@Path("id") int id);
}
