package com.ayoprez.castro.presenter.images;

import android.app.Activity;

import com.ayoprez.castro.ui.fragments.images.ImagesGalleryView;

/**
 * Created by ayo on 17.07.16.
 */
public interface GalleryPresenter {

    void setView(ImagesGalleryView view);
    void changeToolbarVisibility(Activity activity);

}
