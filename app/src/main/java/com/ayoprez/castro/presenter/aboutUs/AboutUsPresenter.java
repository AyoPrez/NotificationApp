package com.ayoprez.castro.presenter.aboutUs;

import com.ayoprez.castro.ui.fragments.aboutus.AboutUsView;

/**
 * Created by ayo on 03.07.16.
 */
public interface AboutUsPresenter {

    void loadAboutUsData();
    void setView(AboutUsView view);

    void openShare();
    void openCall();
    void openMail();

}
