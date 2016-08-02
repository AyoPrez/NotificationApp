package com.ayoprez.castroapp.ui.viewholders.images;

/**
 * Created by ayo on 17.07.16.
 */
public interface GalleryItemView {
    int getItemPosition();

    void displayItemImage(String image);

    void showError(); //TODO how?
}
