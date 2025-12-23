package com.welfare.ws.Interfaces;

import com.welfare.model.Medico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MedicoInterface {

    @GET("medico")
    Call<List<Medico>> list();

    @GET("medico/{id}")
    Call<Medico> find(@Path("id") int id);

    @POST("medico")
    Call<Medico> insert(@Body Medico consultaMedica);

    @PUT("medico/{id}")
    Call<Medico> update(@Path("id") int id, @Body Medico contato);

    @DELETE("medico/{id}")
    Call<Medico> delete(@Path("id") int id);
}
