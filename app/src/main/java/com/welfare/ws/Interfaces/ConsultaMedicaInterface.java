package com.welfare.ws.Interfaces;

import com.welfare.model.ConsultaMedica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ConsultaMedicaInterface {
    @GET("consulta")
    Call<List<ConsultaMedica>> list();

    @GET("consulta/{id}")
    Call<ConsultaMedica> find(@Path("id") int id);

    @GET("consulta/pessoa/{id}")
    Call<List<ConsultaMedica>> findByPerson(@Path("id") int id);

    @POST("consulta")
    Call<ConsultaMedica> insert(@Body ConsultaMedica consultaMedica);

    @PUT("consulta/{id}")
    Call<ConsultaMedica> update(@Path("id") int id, @Body ConsultaMedica consultaMedica);

    @DELETE("consulta/{id}")
    Call<ConsultaMedica> delete(@Path("id") int id);
}
