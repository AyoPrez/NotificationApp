package com.ayoprez.castroapp.ui.viewholders.videos;

/**
 * Created by ayo on 18.08.16.
 */
public interface VideoItemView {

    int getItemPosition();
    void displayItemPreview(String image);

    void showError(); //TODO how?
}
