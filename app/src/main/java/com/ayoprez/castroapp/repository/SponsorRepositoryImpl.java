package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.SponsorItem;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorRepositoryImpl implements SponsorRepository {

    protected Realm sponsorRealm;

    public SponsorRepositoryImpl(){
        sponsorRealm = Realm.getDefaultInstance();
    }

    @Override
    public SponsorItem getSponsor(int id) {
        return sponsorRealm.where(SponsorItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<SponsorItem> getAllSponsors() {
        return new ArrayList<>(sponsorRealm.where(SponsorItem.class).findAll());
    }

    @Override
    public void saveSponsor(final ArrayList<SponsorItem> sponsors) {
        sponsorRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                SponsorItem sponsorTable = sponsorRealm.createObject(SponsorItem.class);
                for (SponsorItem sponsor : sponsors){
                    sponsorTable.setObject(sponsor);
                }
            }
        });
    }

    @Override
    public void closeRealm() {
        sponsorRealm.close();
    }
}
