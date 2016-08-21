package com.ayoprez.castroapp.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 20.08.16.
 */
public class PlayerItemMeta extends RealmObject{
    protected String photo;
    protected String category;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
