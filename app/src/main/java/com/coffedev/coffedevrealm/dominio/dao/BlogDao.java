package com.coffedev.coffedevrealm.dominio.dao;

import io.realm.Realm;

public class BlogDao {
    Realm mReal;

    public BlogDao(Realm mReal) {
        this.mReal = mReal;
    }
}
