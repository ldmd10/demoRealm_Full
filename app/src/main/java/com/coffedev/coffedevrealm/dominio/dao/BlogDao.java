package com.coffedev.coffedevrealm.dominio.dao;

import com.coffedev.coffedevrealm.dominio.entidades.Blog;
import com.coffedev.coffedevrealm.dominio.entidades.Categoria;

import io.realm.Realm;
import io.realm.RealmResults;

public class BlogDao {
    Realm mReal;

    //luisdavidmontoyadiaz
    public BlogDao(Realm mReal) {
        this.mReal = mReal;
    }

    /**
     * Retorna todos los blogs
     * @return
     */
    public RealmResults<Blog> getAllBlogs() {
        return mReal.where(Blog.class).findAll();

    }

    /**
     * Retorna blog asociado a na categoria
     * @param idCategoria
     * @return
     */
    public RealmResults<Blog> getBlogbyCategoria(String idCategoria) {
        Categoria c = mReal.where(Categoria.class).equalTo("id", idCategoria).findFirst();
        return c.getBlogs().where().findAll();
    }

    public Blog getBlog(String idBlog) {
        return mReal.where(Blog.class).contains("id", idBlog).findFirst();
    }
}
