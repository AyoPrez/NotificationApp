package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 17.07.16.
 */
public class ImageItem extends RealmObject{

    @PrimaryKey
    private int id;
    private String image;

    public ImageItem setObject(ImageItem imageItem){
        this.id = imageItem.getId();
        this.image = imageItem.getImage();
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
