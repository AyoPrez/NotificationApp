package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.models.PlayerItem;

import java.util.ArrayList;

/**
 * Created by ayo on 17.07.16.
 */
public interface ImagesGalleryRepository {
    String getImage(int id);
    ArrayList<String> getAllImages();

    void saveImages(ArrayList<ImageItem> images);
    void deleteAllImages();
    void closeRealm();
}
