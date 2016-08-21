package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.models.ImageItemMeta;

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
        return imageRealm.where(ImageItem.class).equalTo("id", id).findFirst().getMeta().getPhoto();
    }

    @Override
    public ArrayList<String> getAllImages() {
        ArrayList<ImageItem> itemList = new ArrayList<>(imageRealm.where(ImageItem.class).findAll());
        ArrayList<String> imagesList = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++){
            imagesList.add(itemList.get(i).getMeta().getPhoto());
        }

        return imagesList;
    }

    @Override
    public void saveImages(final ArrayList<ImageItem> images) {
        ImageItem imageItem;
        ImageItemMeta imageItemMeta;

        Realm imageRealm = Realm.getDefaultInstance();

        for(ImageItem image : images) {
            imageRealm.beginTransaction();

            imageItem = new ImageItem();
            imageItem.setId(image.getId()+getLastId());
            imageItem.setTitle(image.getTitle());

            imageItemMeta = new ImageItemMeta();
            imageItemMeta.setPhoto(image.getMeta().getPhoto());
            imageItem.setMeta(imageItemMeta);

            ImageItemMeta itemMeta = imageRealm.copyToRealm(imageItemMeta);
            ImageItem itemTable = imageRealm.copyToRealm(imageItem);

            imageRealm.commitTransaction();
        }
    }

    @Override
    public void deleteAllImages() {
        imageRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<ImageItem> result = realm.where(ImageItem.class).findAll();
                RealmResults<ImageItemMeta> metaResult = realm.where(ImageItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });
    }

    private int getLastId(){
        return lastId++;
    }

    @Override
    public void closeRealm() {
        imageRealm.close();
    }
}
