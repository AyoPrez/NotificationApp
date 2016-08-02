package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.ImageItem;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryRepositoryImpl implements ImagesGalleryRepository {
    private static final String TAG = ImagesGalleryRepositoryImpl.class.getSimpleName();

    Realm imageRealm;

    public ImagesGalleryRepositoryImpl(){
        imageRealm = Realm.getDefaultInstance();
    }

    @Override
    public String getImage(int id) {
        return imageRealm.where(ImageItem.class).equalTo("id", id).findFirst().getImage();
    }

    @Override
    public ArrayList<String> getAllImages() {
        ArrayList<ImageItem> itemList = new ArrayList<>(imageRealm.where(ImageItem.class).findAll());
        ArrayList<String> imagesList = new ArrayList<>();
        for(int i = 0; i < itemList.size(); i++){
            imagesList.add(itemList.get(i).getImage());
        }

        return imagesList;
    }

    @Override
    public void saveImages(final ArrayList<ImageItem> images) {
        imageRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                ImageItem imageItemTable = imageRealm.createObject(ImageItem.class);

                for(ImageItem image : images){
                    imageItemTable.setObject(image);
                }
            }
        });
    }

    @Override
    public void closeRealm() {
        imageRealm.close();
    }
}
