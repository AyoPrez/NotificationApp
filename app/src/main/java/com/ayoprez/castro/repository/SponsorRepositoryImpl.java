package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.models.SponsorItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorRepositoryImpl implements SponsorRepository {

    protected Realm sponsorRealm;
    protected int lastId;

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
        SponsorItem sponsorItem;
        SponsorItemMeta sponsorItemMeta;

        Realm sponsorRealm = Realm.getDefaultInstance();

        for(SponsorItem sponsor : sponsors) {
            sponsorRealm.beginTransaction();

            sponsorItem = new SponsorItem();
            sponsorItem.setId(sponsor.getId()+getLastId());
            sponsorItem.setTitle(sponsor.getTitle());

            sponsorItemMeta = new SponsorItemMeta();
            sponsorItemMeta.setPhoto(sponsor.getMeta().getPhoto());
            sponsorItemMeta.setUrl(sponsor.getMeta().getUrl());
            sponsorItem.setMeta(sponsorItemMeta);

            SponsorItemMeta itemMeta = sponsorRealm.copyToRealm(sponsorItemMeta);
            SponsorItem itemTable = sponsorRealm.copyToRealm(sponsorItem);

            sponsorRealm.commitTransaction();
        }

        sponsorRealm.close();
    }

    @Override
    public void deleteAllSponsors() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<SponsorItem> result = realm.where(SponsorItem.class).findAll();
                RealmResults<SponsorItemMeta> metaResult = realm.where(SponsorItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    private int getLastId() {
        return lastId++;
    }

    @Override
    public void closeRealm() {
        sponsorRealm.close();
    }
}
