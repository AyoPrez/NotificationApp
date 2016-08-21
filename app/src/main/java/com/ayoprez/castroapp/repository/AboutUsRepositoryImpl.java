package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.models.AboutUsMeta;

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
        AboutUs aboutUsItem = new AboutUs();
        AboutUsMeta aboutUsItemMeta = new AboutUsMeta();

        Realm aboutUsRealm = Realm.getDefaultInstance();

        aboutUsRealm.beginTransaction();

        aboutUsItem.setId(aboutUs.getId());
        aboutUsItem.setTitle(aboutUs.getTitle());

        aboutUsItemMeta.setDescription(aboutUs.getMeta().getDescription());
        aboutUsItemMeta.setShare_image(aboutUs.getMeta().getShare_image());
        aboutUsItemMeta.setShare_text(aboutUs.getMeta().getShare_text());
        aboutUsItemMeta.setEmail(aboutUs.getMeta().getEmail());
        aboutUsItemMeta.setNumber(aboutUs.getMeta().getNumber());
        aboutUsItem.setMeta(aboutUsItemMeta);

        AboutUsMeta itemMeta = aboutUsRealm.copyToRealm(aboutUsItemMeta);
        AboutUs itemUs = aboutUsRealm.copyToRealm(aboutUsItem);

        aboutUsRealm.commitTransaction();
    }

    @Override
    public void deleteAboutUs() {
        aboutUsRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<AboutUs> result = realm.where(AboutUs.class).findAll();
                RealmResults<AboutUsMeta> metaResult = realm.where(AboutUsMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void closeRealm() {
        aboutUsRealm.close();
    }
}
