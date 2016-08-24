package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.AboutUs;

/**
 * Created by ayo on 03.07.16.
 */
public interface AboutUsRepository {
    AboutUs getAboutUs();

    void saveAboutUs(AboutUs aboutUs);
    void deleteAboutUs();
    void closeRealm();
}
