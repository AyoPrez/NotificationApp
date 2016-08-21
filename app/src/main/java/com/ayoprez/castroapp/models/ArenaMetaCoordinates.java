package com.ayoprez.castroapp.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class ArenaMetaCoordinates extends RealmObject{

    private String address;
    private String lat;
    private String lng;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }
}
