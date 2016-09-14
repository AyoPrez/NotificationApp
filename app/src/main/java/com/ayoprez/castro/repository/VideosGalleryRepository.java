package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.VideoItem;

import java.util.ArrayList;

/**
 * Created by ayo on 18.08.16.
 */
public interface VideosGalleryRepository {

    VideoItem getVideo(int id);
    ArrayList<VideoItem> getAllVideos();

    int getIdByPosition(int position);
    VideoItem getItemByPosition(int position);

    void saveVideos(ArrayList<VideoItem> videos);
    void deleteAllVideos();
    void closeRealm();
}
