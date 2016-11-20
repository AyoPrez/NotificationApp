package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.AboutUs;

import rx.Single;

/**
 * Created by ayo on 03.07.16.
 */
public interface AboutUsRepository {
    AboutUs getAboutUs();

    void saveAboutUs(AboutUs aboutUs);
    void deleteAboutUs();
    void closeRealm();
}
