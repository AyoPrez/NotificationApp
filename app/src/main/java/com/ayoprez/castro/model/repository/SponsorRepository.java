package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.SponsorItem;

import java.util.ArrayList;

/**
 * Created by ayo on 17.07.16.
 */
public interface SponsorRepository {
    SponsorItem getSponsor(int id);
    ArrayList<SponsorItem> getAllSponsors();

    int getSponsorIdByPosition(int position);
    SponsorItem getSponsorItemByPosition(int position);

    void saveSponsor(ArrayList<SponsorItem> sponsors);
    void deleteAllSponsors();
    void closeRealm();
}
