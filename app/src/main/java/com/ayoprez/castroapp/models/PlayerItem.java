package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerItem extends RealmObject{

    @PrimaryKey
    protected int id;
    protected String name;
    protected String image;

    public PlayerItem setObject(PlayerItem playerItem){
        this.id = playerItem.id;
        this.name = playerItem.getName();
        this.image = playerItem.getImage();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
