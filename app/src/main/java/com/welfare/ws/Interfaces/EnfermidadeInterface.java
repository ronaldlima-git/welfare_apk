package com.welfare.ws.Interfaces;

import com.welfare.model.Enfermidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EnfermidadeInterface {
    @GET("enfermidade")
    Call<List<Enfermidade>> list();

    @GET("enfermidade/{id}")
    Call<Enfermidade> find(@Path("id") int id);

    @POST("enfermidade")
    Call<Enfermidade> insert(@Body Enfermidade enfermidade);

    @PUT("enfermidade/{id}")
    Call<Enfermidade> update(@Path("id") int id, @Body Enfermidade enfermidade);

    @DELETE("enfermidade/{id}")
    Call<Enfermidade> delete(@Path("id") int id);
}
