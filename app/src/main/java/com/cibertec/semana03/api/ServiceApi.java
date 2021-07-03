package com.cibertec.semana03.api;

import com.cibertec.semana03.entity.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ServiceApi {

    @GET("users")
    public abstract Call<List<User>> listaUsuarios();
}
