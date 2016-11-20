package com.ayoprez.castro.model.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 16.08.16.
 */
public class TableItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private TableItemMeta meta;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public TableItemMeta getMeta() {
        return meta;
    }

    public void setMeta(TableItemMeta meta) {
        this.meta = meta;
    }
}
