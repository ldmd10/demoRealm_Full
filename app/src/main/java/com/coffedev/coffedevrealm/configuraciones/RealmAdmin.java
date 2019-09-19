package com.coffedev.coffedevrealm.configuraciones;

import android.content.Context;

import com.coffedev.coffedevrealm.dominio.dao.BlogDao;
import com.coffedev.coffedevrealm.dominio.dao.CategoriaDao;
import com.coffedev.coffedevrealm.dominio.entidades.Categoria;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmObject;
import io.realm.exceptions.RealmMigrationNeededException;

public class RealmAdmin {
    private static Realm mRealm;
    private static RealmConfiguration configuration;

    /**
     * @param context
     * @return
     */
    public static Realm open(Context context) {

        Realm.init(context);
        configuration = new RealmConfiguration.Builder()
                .schemaVersion(1)
                .name("coffeDev.realm")
                .build();

        try {
            mRealm = Realm.getInstance(configuration);
        } catch (RealmMigrationNeededException r) {
            Realm.deleteRealm(configuration);
            mRealm = Realm.getInstance(configuration);
        }


        return mRealm;
    }

    public static void close() {
        //Cerrar realm
        if (mRealm != null) {
            mRealm.close();
        }
    }

    public static Realm getmRealm() {
        return mRealm;
    }

    public static void deleteAll() {
        mRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();

            }
        });

    }


    public static CategoriaDao CategoriaDao() {
        checkForOpenRealm();
        return new CategoriaDao(mRealm);

    }

    public static BlogDao BlogDao() {
        return new BlogDao(mRealm);

    }


    private static void checkForOpenRealm() {
        if (mRealm == null || mRealm.isClosed()) {
            throw new IllegalStateException("RealmManager: Realm is closed, call open() method first");
        }
    }


}