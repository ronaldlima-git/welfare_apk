package com.welfare.ws.Interfaces;

import com.welfare.model.Telefone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TelefoneInterface {
    @GET("telefone")
    Call<List<Telefone>> list();

    @GET("telefone/{id}")
    Call<Telefone> find(@Path("id") int id);

    @POST("telefone")
    Call<Telefone> insert(@Body Telefone telefone);

    @PUT("telefone/{id}")
    Call<Telefone> update(@Path("id") int id, @Body Telefone telefone);

    @DELETE("telefone")
    Call<Telefone> delete(@Path("id") int id);
}
