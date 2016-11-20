package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.SponsorItem;
import com.ayoprez.castro.model.models.SponsorItemMeta;

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
        return new ArrayList<>(Realm.getDefaultInstance().where(SponsorItem.class).findAll());
    }

    @Override
    public int getSponsorIdByPosition(int position) {
        return getAllSponsors().get(position).getId();
    }

    @Override
    public SponsorItem getSponsorItemByPosition(int position) {
        return getAllSponsors().get(position);
    }

    @Override
    public void saveSponsor(final ArrayList<SponsorItem> sponsors) {
        Realm sponsorRealm = Realm.getDefaultInstance();

        for(SponsorItem sponsor : sponsors) {
            sponsorRealm.beginTransaction();

            sponsor.setId(sponsor.getId() + getLastId());

            SponsorItemMeta itemMeta = sponsorRealm.copyToRealm(sponsor.getMeta());
            SponsorItem itemTable = sponsorRealm.copyToRealm(sponsor);

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
