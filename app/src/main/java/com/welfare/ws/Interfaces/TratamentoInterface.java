package com.welfare.ws.Interfaces;

import com.welfare.model.Tratamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface TratamentoInterface {
    @GET("tratamento")
    Call<List<Tratamento>> list();

    @GET("tratamento/{id}")
    Call<Tratamento> find(@Path("id") int id);

    @POST("tratamento")
    Call<Tratamento> insert(@Body Tratamento tratamento);

    @PUT("tratamento/{id}")
    Call<Tratamento> update(@Path("id") int id, @Body Tratamento tratamento);

    @DELETE("tratamento/{id}")
    Call<Tratamento> delete(@Path("id") int id);
}
