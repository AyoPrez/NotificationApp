package com.ayoprez.castro.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private SponsorItemMeta meta;

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
        this.title = title;
    }

    public SponsorItemMeta getMeta() {
        return meta;
    }

    public void setMeta(SponsorItemMeta meta) {
        this.meta = meta;
    }
}
