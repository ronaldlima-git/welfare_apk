package com.welfare.ws.Interfaces;

import com.welfare.model.Endereco;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnderecoInterface {
    @GET("endereco")
    public Call<List<Endereco>> list();

    @GET("endereco/{id}")
    public Call<Endereco> find(@Path("id") int id);

    @POST("endereco")
    public Call<Endereco> insert(@Body Endereco endereco);

    @PUT("endereco/{id}")
    Call<Endereco> update(@Path("id") int id, @Body Endereco endereco);

    @DELETE("endereco/{id}")
    public Call<?> delete(@Path("id") int id);
}
