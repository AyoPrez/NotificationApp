package com.ayoprez.castro.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerItem extends RealmObject{

    @PrimaryKey
    protected int id;
    protected String title; //Name
    protected PlayerItemMeta meta;

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
        this.title= title;
    }

    public PlayerItemMeta getMeta() {
        return meta;
    }

    public void setMeta(PlayerItemMeta meta) {
        this.meta = meta;
    }
}
