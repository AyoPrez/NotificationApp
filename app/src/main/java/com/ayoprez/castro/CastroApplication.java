package com.ayoprez.castro;

import android.app.Application;

import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.di.DaggerAppComponent;

import com.ayoprez.castro.di.modules.AppModule;
import com.crashlytics.android.Crashlytics;
import com.karumi.dexter.Dexter;
import com.squareup.leakcanary.LeakCanary;

import io.fabric.sdk.android.Fabric;
import io.realm.Realm;
import io.realm.RealmConfiguration;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;


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
        initCalligraphy();
        initLeakCanary();
    }

    private void initLeakCanary(){
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
    }

    private void initCalligraphy(){
        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/PlayfairDisplay-Regular.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build());
    }

    private void initDexter(){
        Dexter.initialize(this);
    }

    private void initRealm(){
        Realm.init(this);

        RealmConfiguration realmConfiguration = new RealmConfiguration.Builder()
                .name("castroApp")
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
