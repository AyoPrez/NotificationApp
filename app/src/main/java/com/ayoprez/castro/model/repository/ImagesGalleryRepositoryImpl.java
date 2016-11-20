package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.ImageItem;
import com.ayoprez.castro.model.models.ImageItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryRepositoryImpl implements ImagesGalleryRepository {
    private static final String TAG = ImagesGalleryRepositoryImpl.class.getSimpleName();

    private Realm imageRealm;
    private int lastId;

    public ImagesGalleryRepositoryImpl(){
        imageRealm = Realm.getDefaultInstance();
    }

    @Override
    public String getImage(int id) {
        return Realm.getDefaultInstance().where(ImageItem.class).equalTo("id", id).findFirst().getMeta().getPhoto();
    }

    @Override
    public ArrayList<String> getAllStringImages() {
        ArrayList<ImageItem> itemList = new ArrayList<>(Realm.getDefaultInstance().where(ImageItem.class).findAll());
        ArrayList<String> imagesList = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++){
            imagesList.add(itemList.get(i).getMeta().getPhoto());
        }

        return imagesList;
    }

    @Override
    public ArrayList<ImageItem> getAllImages() {
        return new ArrayList<>(imageRealm.where(ImageItem.class).findAll());
    }

    @Override
    public int getImageIdByPosition(int position) {
        return getAllImages().get(position).getId();
    }

    @Override
    public String getStringImageByPosition(int position) {
        return getAllStringImages().get(position);
    }

    @Override
    public ImageItem getImageItemByPosition(int position) {
        return getAllImages().get(position);
    }

    @Override
    public void saveImages(final ArrayList<ImageItem> images) {
        Realm imageRealm = Realm.getDefaultInstance();

        for(ImageItem image : images) {
            imageRealm.beginTransaction();
            image.setId(image.getId()+getLastId());

            ImageItemMeta itemMeta = imageRealm.copyToRealm(image.getMeta());
            ImageItem itemTable = imageRealm.copyToRealm(image);

            imageRealm.commitTransaction();
        }

        imageRealm.close();
    }

    @Override
    public void deleteAllImages() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ImageItem> result = realm.where(ImageItem.class).findAll();
                RealmResults<ImageItemMeta> metaResult = realm.where(ImageItemMeta.class).findAll();
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
        imageRealm.close();
    }
}
