package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.ImageItem;

import java.util.ArrayList;

/**
 * Created by ayo on 17.07.16.
 */
public interface ImagesGalleryRepository {
    String getImage(int id);
    ArrayList<String> getAllStringImages();
    ArrayList<ImageItem> getAllImages();
    int getImageIdByPosition(int position);
    String getStringImageByPosition(int position);
    ImageItem getImageItemByPosition(int position);

    void saveImages(ArrayList<ImageItem> images);
    void deleteAllImages();
    void closeRealm();
}
