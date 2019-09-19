package com.coffedev.coffedevrealm.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.adaptadores.ViewPagerAdapter;
import com.coffedev.coffedevrealm.peticiones.api.RxManager;
import com.coffedev.coffedevrealm.peticiones.api.response.ResponseCategorias;
import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.observers.DisposableSingleObserver;

public class CategoriasActivity extends AppCompatActivity {
    String TAG = "coffeDevDemo";
    @BindView(R.id.view_pager_categoria)
    ViewPager viewPager;
    @BindView(R.id.Tab_layout)
    TabLayout tabLayout;
    LinkedList<Fragment> agrupadorViewPager = new LinkedList<>();
    ViewPagerAdapter adaptadorPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);
        cargarCategoria();

        //enlazar views de clase, con los archivos de diseño
        ButterKnife.bind(this);

        adaptadorPager = new ViewPagerAdapter(getSupportFragmentManager(), agrupadorViewPager);
        actualizarViewPager();


    }

    public void cargarCategoria() {
        Log.e(TAG, "cargarCategoria: ");

        try {
            final RxManager rxManager = new RxManager();
            rxManager.obtenerCategoriqas(new DisposableSingleObserver<ResponseCategorias>() {

                @Override
                public void onSuccess(ResponseCategorias responseObtenerData) {
                    //Acciones con la respuesta de la petición
                    Log.d(TAG, "onSuccess: " + responseObtenerData.size());
                }

                @Override
                public void onError(Throwable e) {
                    //Acciones cuano ocurre un error
                    Log.e(TAG, "Response_Error" + e.getMessage());
                }
            });


        } catch (Exception e) {
            String ex = e.getMessage();
            Toast.makeText(getApplicationContext(), "Error en la conexión1", Toast.LENGTH_SHORT).show();
        }
    }


    /**
     * Actualiza el ViewPager con los con los fragments que corresponda
     */
    public void actualizarViewPager() {


    }
}
