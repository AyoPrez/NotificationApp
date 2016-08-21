package com.ayoprez.castroapp.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.di.AppComponent;
import com.ayoprez.castroapp.presenter.SplashPresenter;

import javax.inject.Inject;

/**
 * Created by ayo on 20.08.16.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashPresenter presenter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        startComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppComponent component = ((CastroApplication)getApplication()).getComponent();
        presenter.setView(component, this);
    }

    private void startComponents(){
        ((CastroApplication)getApplication()).getComponent().inject(this);
    }

    @Override
    public void showLoadBar() {

    }

    @Override
    public void changeActivity() {
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    public void showErrorMessage() {

    }
}
