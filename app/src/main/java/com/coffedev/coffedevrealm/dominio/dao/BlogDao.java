package com.coffedev.coffedevrealm.dominio.dao;

import com.coffedev.coffedevrealm.dominio.entidades.Blog;

import io.realm.Realm;
import io.realm.RealmResults;

public class BlogDao {
    Realm mReal;

    public BlogDao(Realm mReal) {
        this.mReal = mReal;
    }


    public RealmResults<Blog> getAllBlogsbyCategoria() {

        return mReal.where(Blog.class).findAll();
    }
}
