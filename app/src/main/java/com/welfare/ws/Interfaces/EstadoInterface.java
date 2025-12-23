package com.welfare.ws.Interfaces;

import com.welfare.model.Estado;
import com.welfare.model.Telefone;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface EstadoInterface {
    @GET("estado")
    Call<List<Estado>> list();

    @GET("estado/{id}")
    Call<Estado> find(@Path("id") int id);

    @PUT("estado/{id}")
    Call<Estado> update(@Path("id") int id, @Body Estado estado);

    @DELETE("estado/{id}")
    Call<Estado> delete(@Path("id") int id);
}
