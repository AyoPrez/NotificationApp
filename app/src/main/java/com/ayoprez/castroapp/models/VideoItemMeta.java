package com.ayoprez.castroapp.models;

import io.realm.RealmObject;

/**
 * Created by ayo on 21.08.16.
 */
public class VideoItemMeta extends RealmObject{

    private String video;
    private String preview;

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getPreview() {
        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
