package com.coffedev.coffedevrealm.peticiones;

import android.app.Activity;

import com.coffedev.coffedevrealm.peticiones.api.APIService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeticionesUtil {


    public static APIService createClienteRX(String url) throws Exception {

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder(); // creo cliente http para peticiones

        httpClient.connectTimeout(10,TimeUnit.SECONDS);
        httpClient.readTimeout(120, TimeUnit.SECONDS);
        httpClient.writeTimeout(120, TimeUnit.SECONDS);

        Gson gson = new GsonBuilder()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .client(httpClient.build())
                .build();

        APIService restClient = retrofit.create(APIService.class);

        return restClient;
    }


}
