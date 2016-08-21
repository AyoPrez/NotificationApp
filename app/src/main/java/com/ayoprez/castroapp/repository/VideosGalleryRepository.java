package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.models.VideoItem;

import java.util.ArrayList;

/**
 * Created by ayo on 18.08.16.
 */
public interface VideosGalleryRepository {

    VideoItem getVideo(int id);
    ArrayList<VideoItem> getAllVideos();

    void saveVideos(ArrayList<VideoItem> videos);
    void deleteAllVideos();
    void closeRealm();
}
