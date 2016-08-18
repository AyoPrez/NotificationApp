package com.ayoprez.castroapp.ui;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.MainPresenter;
import com.ayoprez.castroapp.ui.fragments.SponsorsFragment;
import com.ayoprez.castroapp.ui.fragments.aboutus.AboutUsFragment;
import com.ayoprez.castroapp.ui.fragments.arena.ArenaFragment;
import com.ayoprez.castroapp.ui.fragments.events.EventFragment;
import com.ayoprez.castroapp.ui.fragments.games.GamesFragment;
import com.ayoprez.castroapp.ui.fragments.images.ImagesGalleryFragment;
import com.ayoprez.castroapp.ui.fragments.players.PlayersFragment;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryFragment;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.drawer_layout)
    DrawerLayout drawer;

    @Inject
    MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar(toolbar);

        ButterKnife.bind(this);
        initDrawerMenu();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        switch(item.getItemId()){
            case R.id.nav_events:
                changeFragment(new EventFragment());
                break;
            case R.id.nav_games:
                changeFragment(new GamesFragment());
                break;
            case R.id.nav_players:
                changeFragment(new PlayersFragment());
                break;
            case R.id.nav_arena:
                changeFragment(new ArenaFragment());
                break;
            case R.id.nav_about_us:
                changeFragment(new AboutUsFragment());
                break;
            case R.id.nav_gallery:
                changeFragment(new ImagesGalleryFragment());
                break;
            case R.id.nav_videos:
                changeFragment(new VideosGalleryFragment());
                break;
            case R.id.nav_sponsors:
                changeFragment(new SponsorsFragment());
                break;

            /**TODO Para el próximo domingo.
             * TODO     -Implementar Retrofit
             * TODO     -Probar que la base de datos funciona
             * TODO     -Hacer más tests
              */
        }

        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment fragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }
}
