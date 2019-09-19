package com.coffedev.coffedevrealm.peticiones.api;

import com.coffedev.coffedevrealm.peticiones.api.response.ResponseCategorias;

import io.reactivex.Single;
import retrofit2.http.GET;

public interface APIService {

    @GET("categoryblogs")
    Single<ResponseCategorias> obtenereCategorias(); // devuelve un tipo de observable de rxJava Single

}
