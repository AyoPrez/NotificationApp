package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.models.VideoItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryRepositoryImpl implements VideosGalleryRepository {

    protected Realm videoRealm;
    protected int lastId;

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
    public void saveVideos(final ArrayList<VideoItem> videos) {
        VideoItem videoItem;
        VideoItemMeta videoItemMeta;

        Realm videoRealm = Realm.getDefaultInstance();

        for(VideoItem video : videos) {
            videoRealm.beginTransaction();

            videoItem = new VideoItem();
            videoItem.setId(video.getId()+getLastId());
            videoItem.setTitle(video.getTitle());

            videoItemMeta = new VideoItemMeta();
            videoItemMeta.setPreview(video.getMeta().getPreview());
            videoItemMeta.setVideo(video.getMeta().getVideo());
            videoItem.setMeta(videoItemMeta);

            VideoItemMeta itemMeta = videoRealm.copyToRealm(videoItemMeta);
            VideoItem itemTable = videoRealm.copyToRealm(videoItem);

            videoRealm.commitTransaction();
        }

        videoRealm.close();
    }

    @Override
    public void deleteAllVideos() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<VideoItem> result = realm.where(VideoItem.class).findAll();
                RealmResults<VideoItemMeta> metaResult = realm.where(VideoItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    private int getLastId(){
        return lastId++;
    }

    @Override
    public void closeRealm() {
        videoRealm.close();
    }
}
