package com.ayoprez.castro;

import android.app.Application;

import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.di.DaggerAppComponent;

import com.ayoprez.castro.di.modules.AppModule;
import com.crashlytics.android.Crashlytics;
import com.karumi.dexter.Dexter;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;


/**
 * Created by ayo on 19.06.16.
 */
public class CastroApplication extends Application {

    private AppComponent component;

    @Override
    public void onCreate() {
        super.onCreate();

        Thread threadInitComponents = new Thread() {
            @Override
            public void run() {

                initComponent();
                initRealm();
                initFabric();
                initDexter();
            }
        };

        threadInitComponents.run();
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
