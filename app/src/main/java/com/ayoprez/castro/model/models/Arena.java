package com.ayoprez.castro.model.models;


import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 03.07.16.
 */
public class Arena extends RealmObject{

    @PrimaryKey
    private int id;
    private String title;
    private ArenaMeta meta;

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

    public ArenaMeta getMeta() {
        return meta;
    }

    public void setMeta(ArenaMeta meta) {
        this.meta = meta;
    }
}
