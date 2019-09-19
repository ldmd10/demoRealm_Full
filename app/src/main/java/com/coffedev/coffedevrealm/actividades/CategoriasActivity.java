package com.coffedev.coffedevrealm.actividades;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.adaptadores.ViewPagerAdapter;
import com.coffedev.coffedevrealm.configuraciones.RealmAdmin;
import com.coffedev.coffedevrealm.dominio.dao.CategoriaDao;
import com.coffedev.coffedevrealm.dominio.entidades.Categoria;
import com.coffedev.coffedevrealm.fragmentos.BlogsFragment;
import com.coffedev.coffedevrealm.peticiones.api.RxManager;
import com.coffedev.coffedevrealm.peticiones.api.response.ResponseCategorias;
import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;
import java.util.List;


import io.reactivex.observers.DisposableSingleObserver;
import io.realm.OrderedCollectionChangeSet;
import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;

public class CategoriasActivity extends AppCompatActivity {
    String TAG = "coffeDevDemo";
    ViewPager viewPager;
    TabLayout tabLayout;
    LinkedList<Fragment> agrupadorViewPager = new LinkedList<>();
    ViewPagerAdapter adaptadorPager;
    RealmResults<Categoria> categorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        RealmAdmin.open(this);
        tabLayout = findViewById(R.id.Tab_layout);
        viewPager = findViewById(R.id.view_pager_categoria);
        adaptadorPager = new ViewPagerAdapter(getSupportFragmentManager(), agrupadorViewPager);
        tabLayout.setupWithViewPager(viewPager);
        getSupportActionBar().setElevation(0);
        getSupportActionBar().setDisplayShowTitleEnabled(true);
        setTitle("CoffeDevNews");
        //categorias = RealmAdmin.CategoriaDao().getCategorias("");

        //actualizarViewPager();


        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {


            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    public void cargarCategoria() {


        try {
            final RxManager rxManager = new RxManager();
            rxManager.obtenerCategoriqas(new DisposableSingleObserver<ResponseCategorias>() {

                @Override
                public void onSuccess(ResponseCategorias responseObtenerData) {
                    //Acciones con la respuesta de la petición
                    Log.d(TAG, "onSuccess: " + responseObtenerData.size());

                    for (Categoria categoria : responseObtenerData) {
                        RealmAdmin.CategoriaDao().grabarCategoria(categoria);
                    }
                    actualizarViewPager(responseObtenerData);

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

    public void actualizarViewPager(List<Categoria> categorias) {

        for (Categoria c : categorias) {
            BlogsFragment fragmentBlog = new BlogsFragment();
            Bundle argComponente = new Bundle();
            argComponente.putString("idCategoria", c.getId());
            fragmentBlog.setArguments(argComponente);
            fragmentBlog.setCategoria(c);
            agrupadorViewPager.add(fragmentBlog);
        }

        viewPager.setAdapter(adaptadorPager);
        tabLayout.setupWithViewPager(viewPager);

    }


    /**
     * Crea un menu de opciones
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_opciones, menu);
        return true;
    }

    /**
     * Asigna la accion cuando seleccionen un item del menu
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.descargar:
                cargarCategoria();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RealmAdmin.close();
    }
}
