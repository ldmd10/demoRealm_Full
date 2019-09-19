package com.coffedev.coffedevrealm.adaptadores;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import com.coffedev.coffedevrealm.R;
import com.coffedev.coffedevrealm.adaptadores.interfaces.TouchHelperAdapter;
import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.coffedev.coffedevrealm.viewHolders.ViewHolderBlog;

import java.util.LinkedList;

import javax.annotation.Nullable;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class AdapterBlogs extends RealmRecyclerViewAdapter<Blog, ViewHolderBlog> {
    TouchHelperAdapter touchHelperAdapter;

    public AdapterBlogs(@Nullable OrderedRealmCollection<Blog> data, TouchHelperAdapter touchHelperAdapter) {
        super(data, true);
        this.touchHelperAdapter = touchHelperAdapter;
    }

    @NonNull
    @Override
    public ViewHolderBlog onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolderBlog(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.card_blog, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderBlog holderBlog, int i) {
        final Blog blog = getItem(i);

        holderBlog.bind(blog);
        holderBlog.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                touchHelperAdapter.onClickItem(blog.getId());
            }
        });


    }

    public TouchHelperAdapter getTouchHelperAdapter() {
        return touchHelperAdapter;
    }

    public void setTouchHelperAdapter(TouchHelperAdapter touchHelperAdapter) {
        this.touchHelperAdapter = touchHelperAdapter;
    }


}
