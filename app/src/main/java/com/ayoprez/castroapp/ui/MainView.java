package com.ayoprez.castroapp.ui;

import com.ayoprez.castroapp.common.CommonActivityView;

import java.util.List;

/**
 * Created by ayo on 19.06.16.
 */
public interface MainView<T> extends CommonActivityView{
    void showProgress();

    void hideProgress();
}
