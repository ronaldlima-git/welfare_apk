package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.PlanoSaude;
import com.welfare.ws.CallBack.PlanoSaudeCallBack;
import com.welfare.ws.Interfaces.PlanoSaudeInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class PlanoSaudeFacade {


    private PlanoSaudeInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit =  new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(PlanoSaudeInterface.class);
    }

    public void list(final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<List<PlanoSaude>> callList = planoInterface.list();

        callList.enqueue(new Callback<List<PlanoSaude>>() {
            @Override
            public void onResponse(Call<List<PlanoSaude>> call, Response<List<PlanoSaude>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<PlanoSaude>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<PlanoSaude> callFind = planoInterface.find(id);

        callFind.enqueue(new Callback<PlanoSaude>() {
            @Override
            public void onResponse(Call<PlanoSaude> call, Response<PlanoSaude> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<PlanoSaude> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void findByPerson(int idPerson, final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<List<PlanoSaude>> callFinPerson = planoInterface.findByPerson(idPerson);

        callFinPerson.enqueue(new Callback<List<PlanoSaude>>() {
            @Override
            public void onResponse(Call<List<PlanoSaude>> call, Response<List<PlanoSaude>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<PlanoSaude>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public  void insert(PlanoSaude planoSaude, final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<PlanoSaude> callInsert = planoInterface.insert(planoSaude);

        callInsert.enqueue(new Callback<PlanoSaude>() {
            @Override
            public void onResponse(Call<PlanoSaude> call, Response<PlanoSaude> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<PlanoSaude> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(PlanoSaude planoSaude, final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<PlanoSaude> callUpdate = planoInterface.update(planoSaude.getIdPlano(), planoSaude);

        callUpdate.enqueue(new Callback<PlanoSaude>() {
            @Override
            public void onResponse(Call<PlanoSaude> call, Response<PlanoSaude> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<PlanoSaude> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final PlanoSaudeCallBack callBack){
        PlanoSaudeInterface planoInterface = getApi();

        Call<PlanoSaude> callDelete = planoInterface.delete(id);

        callDelete.enqueue(new Callback<PlanoSaude>() {
            @Override
            public void onResponse(Call<PlanoSaude> call, Response<PlanoSaude> response) {
                if(response.isSuccessful()) {
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<PlanoSaude> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
