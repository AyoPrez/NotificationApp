package com.ayoprez.castro.model.models;

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
        if(preview == null || preview.equals("")) {
            String baseUrl = "http://img.youtube.com/vi/%1$s/0.jpg";

            if (video != null && !video.equals("")) {
                String videoId = video.substring(video.lastIndexOf("?v=") + 3, video.length());
                preview = String.format(baseUrl, videoId);
            }
        }

        return preview;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }
}
