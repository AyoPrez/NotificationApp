package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.VideoItem;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryRepositoryImpl implements VideosGalleryRepository {
    Realm videoRealm;

    public VideosGalleryRepositoryImpl(){
        videoRealm = Realm.getDefaultInstance();
    }

    @Override
    public VideoItem getVideo(int id) {
        return videoRealm.where(VideoItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<VideoItem> getAllVideos() {
        return new ArrayList<>(videoRealm.where(VideoItem.class).findAll());
    }

    @Override
    public void saveVideos(final ArrayList<VideoItem> images) {
        videoRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                VideoItem imageItemTable = videoRealm.createObject(VideoItem.class);

                for(VideoItem image : images){
                    imageItemTable.setObject(image);
                }
            }
        });
    }

    @Override
    public void closeRealm() {
        videoRealm.close();
    }
}
