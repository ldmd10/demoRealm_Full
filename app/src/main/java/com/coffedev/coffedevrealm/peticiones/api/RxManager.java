package com.coffedev.coffedevrealm.peticiones.api;

import android.app.Activity;
import android.util.Log;

import com.coffedev.coffedevrealm.peticiones.PeticionesUtil;
import com.coffedev.coffedevrealm.peticiones.api.response.ResponseCategorias;

import java.util.HashMap;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

public class RxManager {
    private Activity observador;
    APIService restCliente;
    private CompositeDisposable disposable = new CompositeDisposable();

    public RxManager(Activity activity) throws Exception {
        this.observador = activity;
        try {
            PeticionesUtil.createClienteRX("https://api.cocanasa.org/", activity);
        } catch (Exception e) {
            Log.d("ErrorRetrofitCliente", "RxManager: " + e.getMessage());
        }
    }

    public void obtenerCategoriqas(DisposableSingleObserver<ResponseCategorias> observer) {

        HashMap<String, String> header = new HashMap<>();

        Disposable disposable_aux = restCliente.obtenereCategorias().subscribeOn(Schedulers.io()).
                observeOn(AndroidSchedulers.mainThread()).
                subscribeWith(observer);
        disposable.add(disposable_aux);

    }


}
