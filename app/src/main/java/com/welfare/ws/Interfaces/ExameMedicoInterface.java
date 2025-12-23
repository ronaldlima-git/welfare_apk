package com.welfare.ws.Interfaces;

import com.welfare.model.ExameMedico;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ExameMedicoInterface {
    @GET("exame")
    Call<List<ExameMedico>> list();

    @GET("exame/{id}")
    Call<ExameMedico> find(@Path("id") int id);

    //TODO implement this method in back-end
    @GET("exame/consulta/{idConsulta}")
    Call<List<ExameMedico>> findByConsulta(@Path("id") int id);

    @POST("exame")
    Call<ExameMedico> insert(@Body ExameMedico exameMedico);

    @PUT("exame/{id}")
    Call<ExameMedico> update(@Path("id") int id, @Body ExameMedico exameMedico);

    @DELETE("exame/{id}")
    Call<ExameMedico> delete(@Path("id") int id);
}
