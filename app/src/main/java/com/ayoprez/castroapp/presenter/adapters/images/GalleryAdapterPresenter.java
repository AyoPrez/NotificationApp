package com.ayoprez.castroapp.presenter.adapters.images;

import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemView;

/**
 * Created by ayo on 17.07.16.
 */
public interface GalleryAdapterPresenter {

    void setView(GalleryItemView view);
    void loadImages();

    int getImagesCountSize();
}
