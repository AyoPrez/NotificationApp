package com.ayoprez.castroapp.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 03.07.16.
 */

public class AboutUs extends RealmObject {

    @PrimaryKey
    private int id;
    private String name;
    private String description;
    private String shareText;
    private String telephoneNumber;
    private String mail;
    private String image;

    public AboutUs setObject(AboutUs object){
        this.id = object.getId();
        this.name = object.getName();
        this.description = object.getDescription();
        this.shareText = object.getShareText();
        this.telephoneNumber = object.getTelephoneNumber();
        this.mail = object.getMail();
        this.image = object.getImage();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShareText() {
        return shareText;
    }

    public void setShareText(String shareText) {
        this.shareText = shareText;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
