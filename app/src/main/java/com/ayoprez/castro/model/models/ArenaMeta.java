package com.ayoprez.castro.model.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class ArenaMeta extends RealmObject{

    private ArenaMetaCoordinates coordinates;
    private String description;
    private String photo;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public ArenaMetaCoordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(ArenaMetaCoordinates coordinates) {
        this.coordinates = coordinates;
    }
}
