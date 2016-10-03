package com.ayoprez.castro.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.presenter.MainPresenter;
import com.ayoprez.castro.ui.fragments.events.EventFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersBaseFragment;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsFragment;
import com.ayoprez.castro.ui.fragments.aboutus.AboutUsFragment;
import com.ayoprez.castro.ui.fragments.arena.ArenaFragment;
import com.ayoprez.castro.ui.fragments.events.EventListFragment;
import com.ayoprez.castro.ui.fragments.games.GamesFragment;
import com.ayoprez.castro.ui.fragments.images.ImagesGalleryFragment;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Inject
    MainPresenter mainPresenter;

    @Inject
    ErrorNotification errorNotification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);

        toolbar.setTitle(R.string.app_name);

        initDrawerMenu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if((getIntent() != null && getIntent().getStringExtra("action") != null) && getIntent().getStringExtra("action").equals("EVENT")){
            changeToEvent(getIntent().getExtras());
        } else {
            changeFragment(new EventListFragment());
        }
    }

    public Toolbar getToolbar(){
        return toolbar;
    }

    private void changeToEvent(Bundle eventBundle){
        EventFragment eventFragment = new EventFragment();
        eventFragment.setArguments(eventBundle);

        changeFragment(eventFragment);
    }

    private void initDrawerMenu(){
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_events:
                changeFragment(EventListFragment.getInstance());
                break;
            case R.id.nav_games:
                changeFragment(GamesFragment.getInstance());
                break;
            case R.id.nav_players:
                changeFragment(PlayersBaseFragment.getInstance());
                break;
            case R.id.nav_arena:
                changeFragment(ArenaFragment.getInstance());
                break;
            case R.id.nav_about_us:
                changeFragment(AboutUsFragment.getInstance());
                break;
            case R.id.nav_gallery:
                changeFragment(ImagesGalleryFragment.getInstance());
                break;
            case R.id.nav_videos:
                changeFragment(VideosGalleryFragment.getInstance());
                break;
            case R.id.nav_sponsors:
                changeFragment(SponsorsFragment.getInstance());
                break;
//            case R.id.nav_settings:
//                break;
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .attach(fragment)
                .commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        errorNotification.showNotification(this, getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }
}
