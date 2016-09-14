package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.ImageItem;
import com.ayoprez.castro.models.ImageItemMeta;

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
    public ArrayList<String> getAllStringImages() {
        ArrayList<ImageItem> itemList = new ArrayList<>(imageRealm.where(ImageItem.class).findAll());
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
