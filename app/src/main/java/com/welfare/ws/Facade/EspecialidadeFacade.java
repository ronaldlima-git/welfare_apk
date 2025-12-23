package com.welfare.ws.Facade;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.welfare.model.Especialidade;
import com.welfare.ws.CallBack.EspecialidadeCallBack;
import com.welfare.ws.Interfaces.EspecialidadeInterface;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.welfare.ws.UrlApiServices.API_WELFARE;

public class EspecialidadeFacade {

    private EspecialidadeInterface getApi(){
        Gson g = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_WELFARE)
                .addConverterFactory(GsonConverterFactory.create(g))
                .build();
        return retrofit.create(EspecialidadeInterface.class);
    }

    public void list(final EspecialidadeCallBack callBack){
        EspecialidadeInterface especialidadeInterface = getApi();

        Call<List<Especialidade>> callList = especialidadeInterface.list();

        callList.enqueue(new Callback<List<Especialidade>>() {
            @Override
            public void onResponse(Call<List<Especialidade>> call, Response<List<Especialidade>> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<List<Especialidade>> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void find(int id, final EspecialidadeCallBack callBack){
        EspecialidadeInterface especialidadeInterface = getApi();

        Call<Especialidade> callFind = especialidadeInterface.find(id);

        callFind.enqueue(new Callback<Especialidade>() {
            @Override
            public void onResponse(Call<Especialidade> call, Response<Especialidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Especialidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void insert(Especialidade especialidade, final EspecialidadeCallBack callBack){
        EspecialidadeInterface especialidadeInterface = getApi();

        Call<Especialidade> callInsert = especialidadeInterface.insert(especialidade);

        callInsert.enqueue(new Callback<Especialidade>() {
            @Override
            public void onResponse(Call<Especialidade> call, Response<Especialidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Especialidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void update(Especialidade especialidade, final EspecialidadeCallBack callBack){
        EspecialidadeInterface especialidadeInterface = getApi();

        Call<Especialidade> callUpdate = especialidadeInterface.update(especialidade.getIdEspecialidade(), especialidade);

        callUpdate.enqueue(new Callback<Especialidade>() {
            @Override
            public void onResponse(Call<Especialidade> call, Response<Especialidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Especialidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }

    public void delete(int id, final EspecialidadeCallBack callBack){
        EspecialidadeInterface especialidadeInterface = getApi();

        Call<Especialidade> callDelete = especialidadeInterface.delete(id);

        callDelete.enqueue(new Callback<Especialidade>() {
            @Override
            public void onResponse(Call<Especialidade> call, Response<Especialidade> response) {
                if(response.isSuccessful()){
                    callBack.onSucess(response.body());
                }else{
                    callBack.onFailure(new Exception(response.errorBody().toString()));
                }
            }

            @Override
            public void onFailure(Call<Especialidade> call, Throwable t) {
                callBack.onFailure(t);
            }
        });
    }
}
