package com.ayoprez.castro.presenter.adapters.images;

import com.ayoprez.castro.ui.viewholders.images.GalleryItemView;

/**
 * Created by ayo on 17.07.16.
 */
public interface GalleryAdapterPresenter {

    void setView(GalleryItemView view);
    void loadImages();

    int getImagesCountSize();
}
