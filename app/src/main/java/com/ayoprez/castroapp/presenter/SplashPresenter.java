package com.ayoprez.castroapp.presenter;

import com.ayoprez.castroapp.di.AppComponent;
import com.ayoprez.castroapp.ui.SplashView;

/**
 * Created by ayo on 20.08.16.
 */
public interface SplashPresenter {

    void setView(AppComponent component, SplashView view);
    void getDataFromServer();
}
