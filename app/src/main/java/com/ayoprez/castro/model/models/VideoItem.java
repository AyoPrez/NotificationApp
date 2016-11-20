package com.ayoprez.castro.model.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 18.08.16.
 */
public class VideoItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String title;
    private VideoItemMeta meta;

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

    public VideoItemMeta getMeta() {
        return meta;
    }

    public void setMeta(VideoItemMeta meta) {
        this.meta = meta;
    }
}
