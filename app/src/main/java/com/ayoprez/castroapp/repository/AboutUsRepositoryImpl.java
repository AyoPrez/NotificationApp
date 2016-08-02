package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.repository.AboutUsRepository;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsRepositoryImpl implements AboutUsRepository {

    private Realm aboutUsRealm;

    public AboutUsRepositoryImpl(){
        aboutUsRealm = Realm.getDefaultInstance();
    }

    @Override
    public AboutUs getAboutUs() {
        return aboutUsRealm.where(AboutUs.class).findFirst();
    }

    @Override
    public void saveAboutUs(final AboutUs aboutUs) {
        aboutUsRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                AboutUs aboutUsTable = aboutUsRealm.createObject(AboutUs.class);
                aboutUsTable.setObject(aboutUs);
            }
        });
    }

    @Override
    public void closeRealm() {
        aboutUsRealm.close();
    }
}
