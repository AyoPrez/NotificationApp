package com.ayoprez.castroapp.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 03.07.16.
 */
public class Arena extends RealmObject{

    @PrimaryKey
    private int id;
    private String name;
    private String address;
    private String description;
    private String image;

    public Arena setObject(Arena arena){
        this.id = arena.getId();
        this.name = arena.getName();
        this.address = arena.getAddress();
        this.description = arena.getDescription();
        this.image = arena.getImage();
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
