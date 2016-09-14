package com.ayoprez.castro.ui.fragments.images;

import android.support.v4.app.Fragment;

import com.ayoprez.castro.common.CommonListView;
import com.ayoprez.castro.ui.fragments.ImageFragment;

/**
 * Created by ayo on 17.07.16.
 */
public interface ImagesGalleryView extends CommonListView {
    void changeFragment(Fragment fragment);
}
