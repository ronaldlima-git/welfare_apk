package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Medicamento;
import com.welfare.ws.CallBack.MedicamentoCallBack;
import com.welfare.ws.Interfaces.MedicamentoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class MedicamentoFacade {
    private MedicamentoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(MedicamentoInterface.class);
    }

    public void list(final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<List<Medicamento>> callList = medicamentoInterface.list();

        callList.enqueue(new Callback<List<Medicamento>>() {
            @Override
            public void onResponse(Call<List<Medicamento>> call, Response<List<Medicamento>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Medicamento>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<Medicamento> callFind = medicamentoInterface.find(id);

        callFind.enqueue(new Callback<Medicamento>() {
            @Override
            public void onResponse(Call<Medicamento> call, Response<Medicamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medicamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void findByEnfermidade(int idEnfermidade, final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<List<Medicamento>> callFindEnfermidade = medicamentoInterface.findByEnfermidade(idEnfermidade);

        callFindEnfermidade.enqueue(new Callback<List<Medicamento>>() {
            @Override
            public void onResponse(Call<List<Medicamento>> call, Response<List<Medicamento>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Medicamento>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Medicamento medicamento, final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<Medicamento> callInsert = medicamentoInterface.insert(medicamento);

        callInsert.enqueue(new Callback<Medicamento>() {
            @Override
            public void onResponse(Call<Medicamento> call, Response<Medicamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medicamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Medicamento medicamento, final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<Medicamento> callUpdate = medicamentoInterface.update(medicamento.getIdMedicamento(), medicamento);

        callUpdate.enqueue(new Callback<Medicamento>() {
            @Override
            public void onResponse(Call<Medicamento> call, Response<Medicamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medicamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final MedicamentoCallBack callBack){
        MedicamentoInterface medicamentoInterface = getApi();

        Call<Medicamento> callDelete = medicamentoInterface.delete(id);

        callDelete.enqueue(new Callback<Medicamento>() {
            @Override
            public void onResponse(Call<Medicamento> call, Response<Medicamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Medicamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
