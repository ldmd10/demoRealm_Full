package com.coffedev.coffedevrealm.dominio.dao;

import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.coffedev.coffedevrealm.dominio.entidades.Categoria;

import io.realm.Realm;
import io.realm.RealmList;
import io.realm.RealmResults;

public class BlogDao {
    Realm mReal;
//luisdavidmontoyadiaz
    public BlogDao(Realm mReal) {
        this.mReal = mReal;
    }


    public RealmResults<Blog> getAllBlogs(String idCategoria) {
        Categoria c=mReal.where(Categoria.class).equalTo("id", idCategoria).findFirst();
        return c.getBlogs().where().findAll();

        //return mReal.where(Blog.class).findAll();
    }

    public RealmResults<Blog> getBlogbyCategoria(String idCategoria) {
        Categoria c = mReal.where(Categoria.class).equalTo("id", idCategoria).findFirst();
        return c.getBlogs().where().findAll();
    }

    public Blog getBlog(String idBlog) {
        return mReal.where(Blog.class).contains("id", idBlog).findFirst();
    }
}
