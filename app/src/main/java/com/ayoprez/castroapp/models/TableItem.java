package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 16.08.16.
 */
public class TableItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String image;

    public TableItem setObject(TableItem tableItem){
        this.id = tableItem.getId();
        this.image = tableItem.getImage();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
