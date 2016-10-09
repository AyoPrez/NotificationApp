package com.ayoprez.castro.ui;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.presenter.SplashPresenter;

import javax.inject.Inject;

import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

/**
 * Created by ayo on 20.08.16.
 */
public class SplashActivity extends AppCompatActivity implements SplashView {
    private static final String TAG = SplashActivity.class.getSimpleName();

    @Inject
    SplashPresenter presenter;

    @Inject
    ErrorNotification errorNotification;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        startComponents();
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppComponent component = ((CastroApplication)getApplication()).getComponent();
        presenter.setView(component, this);
        presenter.getDataFromServer(isConnected(), isWiFi());
    }

    private void startComponents(){
        ((CastroApplication)getApplication()).getComponent().inject(this);
    }

    @Override
    public void showLoadBar() {

    }

    private NetworkInfo getNetworkInfo(){
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo();
    }

    private boolean isConnected(){
        NetworkInfo activeNetwork = getNetworkInfo();

        return activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
    }

    private boolean isWiFi(){
        return getNetworkInfo() != null && getNetworkInfo().getType() == ConnectivityManager.TYPE_WIFI;
    }

    @Override
    public void changeActivity() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        if(Looper.getMainLooper().getThread() == Thread.currentThread()) {
            errorNotification.showNotification(this, getResources().getStringArray(R.array.errorsArray)[errorMessage]);
        }
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
