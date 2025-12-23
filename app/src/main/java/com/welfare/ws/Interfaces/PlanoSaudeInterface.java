package com.welfare.ws.Interfaces;

import com.welfare.model.PlanoSaude;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface PlanoSaudeInterface {
    @GET("planoSaude")
    Call<List<PlanoSaude>> list();

    @GET("planoSaude/{id}")
    Call<PlanoSaude> find(@Path("id") int id);

    @GET("planoSaude/person/{id}")
    Call<List<PlanoSaude>> findByPerson(@Path("id") int id);

    @POST("planoSaude")
    Call<PlanoSaude> insert(@Body PlanoSaude planoSaude);

    @PUT("planoSaude/{id}")
    Call<PlanoSaude> update(@Path("id") int id, @Body PlanoSaude planoSaude);

    @DELETE("planoSaude/{id}")
    Call<PlanoSaude> delete(@Path("id") int id);
}