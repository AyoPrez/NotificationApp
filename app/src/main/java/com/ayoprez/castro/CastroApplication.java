package com.ayoprez.castro;

import android.app.Application;
import android.content.Context;

import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.di.DaggerAppComponent;

import com.crashlytics.android.Crashlytics;
import com.karumi.dexter.Dexter;

import java.security.SecureRandom;

import io.fabric.sdk.android.Fabric;
import io.realm.DynamicRealm;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmMigration;
import io.realm.RealmSchema;


/**
 * Created by ayo on 19.06.16.
 */
public class CastroApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        initComponent();
        initRealm();
        initFabric();
        initDexter();
    }

    private void initDexter(){
        Dexter.initialize(this);
    }

    private void initRealm(){

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("realmTeam")
                .schemaVersion(R.string.app_version)
                .deleteRealmIfMigrationNeeded()
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

    private void initFabric(){
        Fabric.with(this, new Crashlytics());
    }

    public AppComponent getComponent() {
        return component;
    }

    private void initComponent() {
        component = DaggerAppComponent.builder()
                .appModule(new AppModule())
                .build();
    }

}
