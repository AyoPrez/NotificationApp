package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.AboutUs;
import com.ayoprez.castro.model.models.AboutUsMeta;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.Observer;
import rx.Single;
import rx.Subscription;
import rx.functions.Action1;

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
        return Realm.getDefaultInstance().where(AboutUs.class).findFirst();
    }

    @Override
    public void saveAboutUs(final AboutUs aboutUs) {
        Realm aboutUsRealm = Realm.getDefaultInstance();

        aboutUsRealm.beginTransaction();

        AboutUsMeta itemMeta = aboutUsRealm.copyToRealm(aboutUs.getMeta());
        AboutUs itemUs = aboutUsRealm.copyToRealm(aboutUs);

        aboutUsRealm.commitTransaction();

        aboutUsRealm.close();
    }

    @Override
    public void deleteAboutUs() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<AboutUs> result = realm.where(AboutUs.class).findAll();
                RealmResults<AboutUsMeta> metaResult = realm.where(AboutUsMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void closeRealm() {
        aboutUsRealm.close();
    }
}
