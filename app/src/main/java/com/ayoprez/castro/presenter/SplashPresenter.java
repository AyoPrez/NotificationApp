package com.ayoprez.castro.presenter;

import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.ui.SplashView;

/**
 * Created by ayo on 20.08.16.
 */
public interface SplashPresenter {

    void setView(AppComponent component, SplashView view);
    void getDataFromServer(boolean isConnected, boolean isWiFi);
}
