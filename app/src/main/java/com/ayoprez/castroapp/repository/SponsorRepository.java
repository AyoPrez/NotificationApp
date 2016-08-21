package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.SponsorItem;

import java.util.ArrayList;

/**
 * Created by ayo on 17.07.16.
 */
public interface SponsorRepository {
    SponsorItem getSponsor(int id);
    ArrayList<SponsorItem> getAllSponsors();

    void saveSponsor(ArrayList<SponsorItem> sponsors);
    void deleteAllSponsors();
    void closeRealm();
}
