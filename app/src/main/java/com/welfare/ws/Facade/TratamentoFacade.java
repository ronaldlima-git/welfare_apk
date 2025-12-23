package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Tratamento;
import com.welfare.ws.CallBack.TratamentoCallBack;
import com.welfare.ws.Interfaces.TratamentoInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class TratamentoFacade {
    private TratamentoInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(TratamentoInterface.class);
    }

    public void list(final TratamentoCallBack callBack){
        TratamentoInterface tratamentoInterface = getApi();

        Call<List<Tratamento>> callList = tratamentoInterface.list();

        callList.enqueue(new Callback<List<Tratamento>>() {
            @Override
            public void onResponse(Call<List<Tratamento>> call, Response<List<Tratamento>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Tratamento>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final TratamentoCallBack callBack){
        TratamentoInterface tratamentoInterface = getApi();

        Call<Tratamento> callFind = tratamentoInterface.find(id);

        callFind.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Tratamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Tratamento tratamento, final TratamentoCallBack callBack){
        TratamentoInterface tratamentoInterface = getApi();

        Call<Tratamento> callInsert = tratamentoInterface.insert(tratamento);

        callInsert.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Tratamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Tratamento tratamento, final TratamentoCallBack callBack){
        TratamentoInterface tratamentoInterface = getApi();

        Call<Tratamento> callUpdate = tratamentoInterface.update(tratamento.getIdTratamento(), tratamento);

        callUpdate.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Tratamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final TratamentoCallBack callBack){
        TratamentoInterface tratamentoInterface = getApi();

        Call<Tratamento> callDelete = tratamentoInterface.delete(id);

        callDelete.enqueue(new Callback<Tratamento>() {
            @Override
            public void onResponse(Call<Tratamento> call, Response<Tratamento> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Tratamento> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
