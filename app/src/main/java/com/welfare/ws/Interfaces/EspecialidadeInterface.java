package com.welfare.ws.Interfaces;

import com.welfare.model.Especialidade;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EspecialidadeInterface {
    @GET("especialidade")
    Call<List<Especialidade>> list();

    @GET("especialidade/{id}")
    Call<Especialidade> find(@Path("id") int id);

    @POST("especialidade")
    Call<Especialidade> insert(@Body Especialidade especialidade);

    @PUT("especialidade/{id}")
    Call<Especialidade> update(@Path("id") int id, @Body Especialidade especialidade);

    @DELETE("especialidade/{id}")
    Call<Especialidade> delete(@Path("id") int id);
}
