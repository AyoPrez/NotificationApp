package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String image;
    private String url;

    public SponsorItem setObject(SponsorItem item){
        this.id = item.getId();
        this.image = item.getImage();
        this.url = item.getUrl();
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
