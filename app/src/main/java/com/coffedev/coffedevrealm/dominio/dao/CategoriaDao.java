package com.coffedev.coffedevrealm.dominio.dao;

import com.coffedev.coffedevrealm.dominio.entidades.Categoria;

import io.realm.Realm;
import io.realm.RealmResults;

public class CategoriaDao {
    Realm mReal;

    public CategoriaDao(Realm mReal) {
        this.mReal = mReal;
    }

    public void grabarCategoria(final Categoria categoria) {
        mReal.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                mReal.insertOrUpdate(categoria);
            }
        });
    }

    public RealmResults<Categoria> getCategorias(String filtro) {
        return mReal.where(Categoria.class).contains("name", filtro).findAll();
    }


}
