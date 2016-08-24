package com.ayoprez.castro.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class ImageItemMeta extends RealmObject{

    private String photo;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
