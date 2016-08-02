package com.ayoprez.castroapp;

import android.app.Application;

import com.ayoprez.castroapp.di.AppComponent;
import com.ayoprez.castroapp.di.DaggerAppComponent;

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
        initComponent();
        initRealm();
    }

    private void initRealm(){
        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder(this)
                .name("realmTeam")
                .build();

        Realm.setDefaultConfiguration(realmConfiguration);
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
