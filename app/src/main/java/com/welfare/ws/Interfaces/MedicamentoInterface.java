package com.welfare.ws.Interfaces;

import com.welfare.model.Medicamento;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MedicamentoInterface {
    @GET("medicamento")
    Call<List<Medicamento>> list();

    @GET("medicamento/{id}")
    Call<Medicamento> find(@Path("id") int id);

    //TODO implements this method in back-end
    @GET("medicamento/enfermidade/{idEnfermidade}")
    Call<List<Medicamento>> findByEnfermidade(@Path("idEnfermidade") int id);

    @POST("medicamento")
    Call<Medicamento> insert(@Body Medicamento medicamento);

    @PUT("medicamento/{id}")
    Call<Medicamento> update(@Path("id") int id, @Body Medicamento medicamento);

    @DELETE("medicamento/{id}")
    Call<Medicamento> delete(@Path("id") int id);
}
