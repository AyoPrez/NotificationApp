package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.VideoItem;
import com.ayoprez.castro.model.models.VideoItemMeta;

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
        return new ArrayList<>(Realm.getDefaultInstance().where(VideoItem.class).findAll());
    }

    @Override
    public int getIdByPosition(int position) {
        return getAllVideos().get(position).getId();
    }

    @Override
    public VideoItem getItemByPosition(int position) {
        return getAllVideos().get(position);
    }

    @Override
    public void saveVideos(final ArrayList<VideoItem> videos) {
        Realm videoRealm = Realm.getDefaultInstance();

        for(VideoItem video : videos) {
            videoRealm.beginTransaction();

            video.setId(video.getId()+getLastId());
            video.getMeta().setPreview(video.getMeta().getPreview());

            VideoItemMeta itemMeta = videoRealm.copyToRealm(video.getMeta());
            VideoItem itemTable = videoRealm.copyToRealm(video);

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
