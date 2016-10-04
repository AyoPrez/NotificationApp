package com.ayoprez.castro.ui;

import com.ayoprez.castro.common.CommonActivityView;

import java.util.ArrayList;

/**
 * Created by ayo on 19.06.16.
 */
public interface MainView<T> extends CommonActivityView{
    void showProgress();

    void hideProgress();

    void initImageGallery(ArrayList<String> picturesUrl);
}
