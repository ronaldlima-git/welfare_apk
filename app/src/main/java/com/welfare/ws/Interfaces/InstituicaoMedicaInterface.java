package com.welfare.ws.Interfaces;

import com.welfare.model.InstituicaoMedica;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface InstituicaoMedicaInterface {

    @GET("instituicao")
    Call<List<InstituicaoMedica>> list();

    @GET("instituicao/{id}")
    Call<InstituicaoMedica> find(@Path("id") int id);

    @POST("instituicao")
    Call<InstituicaoMedica> insert(@Body InstituicaoMedica instituicaoMedica);

    @PUT("instituicao/{id}")
    Call<InstituicaoMedica> update(@Path("id") int id, @Body InstituicaoMedica instituicaoMedica);

    @DELETE("especialidade/{id}")
    Call<InstituicaoMedica> delete(@Path("id") int id);
}
