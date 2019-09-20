package com.coffedev.coffedevrealm.fragmentos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.actividades.BlogActivity;
import com.coffedev.coffedevrealm.adaptadores.AdapterBlogs;
import com.coffedev.coffedevrealm.adaptadores.interfaces.TouchHelperAdapter;
import com.coffedev.coffedevrealm.adaptadores.interfaces.ViewPager;
import com.coffedev.coffedevrealm.configuraciones.RealmAdmin;
import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.coffedev.coffedevrealm.dominio.entidades.Categoria;

import io.realm.RealmResults;

public class BlogsFragment extends Fragment implements ViewPager {

    RecyclerView visorBlog;
    RealmResults<Blog> blogsCategoria;
    private String idCategoria;
    Categoria categoria;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //Recibir argumentos
        idCategoria=getArguments().getString("idCategoria");
        return inflater.inflate(R.layout.fragment_blogs, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        visorBlog = view.findViewById(R.id.visor_blogs);
        visorBlog.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        visorBlog.setLayoutManager(layoutManager);
        update();

    }

    /**
     * Actualiza los elementos del recyclerView
     */
    public void update() {
        Log.d("oeoeoe", "update: abriendo " + categoria.getName());
        blogsCategoria = RealmAdmin.BlogDao().getBlogbyCategoria(idCategoria);

        AdapterBlogs adapterBlogs = new AdapterBlogs(blogsCategoria, new TouchHelperAdapter() {
            @Override
            public void onClickItem(String id) {
                Intent intent = new Intent(getContext(), BlogActivity.class);
                intent.putExtra("idBlog", id);
                startActivity(intent);
            }
        });
        visorBlog.setAdapter(adapterBlogs);


    }

    @Override
    public String getTitle() {
        return categoria.getName();
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
}
