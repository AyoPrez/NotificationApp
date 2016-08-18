package com.ayoprez.castroapp.models;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by ayo on 18.08.16.
 */
public class VideoItem extends RealmObject {

    @PrimaryKey
    private int id;
    private String videoUrl;
    private String previewImageUrl;

    public VideoItem setObject(VideoItem imageItem){
        this.id = imageItem.getId();
        this.videoUrl = imageItem.getVideoUrl();
        this.previewImageUrl = imageItem.getPreviewImageUrl();
        return this;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getPreviewImageUrl() {
        return previewImageUrl;
    }

    public void setPreviewImageUrl(String previewImageUrl) {
        this.previewImageUrl = previewImageUrl;
    }
}
