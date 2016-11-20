package com.ayoprez.castro.model.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class TableItemMeta extends RealmObject{

    private String tabla;

    public String getTabla() {
        return tabla;
    }

    public void setTabla(String tabla) {
        this.tabla = tabla;
    }
}
