package com.ayoprez.castroapp.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class SponsorItemMeta extends RealmObject{

    private String photo;
    private String url;

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
