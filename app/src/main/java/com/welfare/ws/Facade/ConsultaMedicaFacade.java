package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.ConsultaMedica;
import com.welfare.ws.CallBack.ConsultaMedicaCallBack;
import com.welfare.ws.Interfaces.ConsultaMedicaInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class ConsultaMedicaFacade {
    private ConsultaMedicaInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(ConsultaMedicaInterface.class);
    }

    public void list(final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<List<ConsultaMedica>> callList = consultaInterface.list();

        callList.enqueue(new Callback<List<ConsultaMedica>>() {
            @Override
            public void onResponse(Call<List<ConsultaMedica>> call, Response<List<ConsultaMedica>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaMedica>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<ConsultaMedica> callFind = consultaInterface.find(id);

        callFind.enqueue(new Callback<ConsultaMedica>() {
            @Override
            public void onResponse(Call<ConsultaMedica> call, Response<ConsultaMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ConsultaMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void findByPerson(int idPerson, final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<List<ConsultaMedica>> callFindPerson = consultaInterface.findByPerson(idPerson);

        callFindPerson.enqueue(new Callback<List<ConsultaMedica>>() {
            @Override
            public void onResponse(Call<List<ConsultaMedica>> call, Response<List<ConsultaMedica>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<ConsultaMedica>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(ConsultaMedica consultaMedica, final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<ConsultaMedica> callInsert = consultaInterface.insert(consultaMedica);

        callInsert.enqueue(new Callback<ConsultaMedica>() {
            @Override
            public void onResponse(Call<ConsultaMedica> call, Response<ConsultaMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else {
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ConsultaMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(ConsultaMedica consultaMedica, final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<ConsultaMedica> callUpdate = consultaInterface.update(consultaMedica.getIdConsulta(), consultaMedica);

        callUpdate.enqueue(new Callback<ConsultaMedica>() {
            @Override
            public void onResponse(Call<ConsultaMedica> call, Response<ConsultaMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ConsultaMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final ConsultaMedicaCallBack callBack){
        ConsultaMedicaInterface consultaInterface = getApi();

        Call<ConsultaMedica> callDelete = consultaInterface.delete(id);

        callDelete.enqueue(new Callback<ConsultaMedica>() {
            @Override
            public void onResponse(Call<ConsultaMedica> call, Response<ConsultaMedica> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<ConsultaMedica> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
