package com.ayoprez.castro.ui;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.presenter.MainPresenter;
import com.ayoprez.castro.ui.fragments.PreferencesFragment;
import com.ayoprez.castro.ui.fragments.events.EventFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersBaseFragment;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsFragment;
import com.ayoprez.castro.ui.fragments.aboutus.AboutUsFragment;
import com.ayoprez.castro.ui.fragments.arena.ArenaFragment;
import com.ayoprez.castro.ui.fragments.events.EventListFragment;
import com.ayoprez.castro.ui.fragments.games.GamesFragment;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryFragment;
import com.ayoprez.iggy.library.ImageGalleryFragment;
import com.ayoprez.iggy.library.PaletteHelper;
import com.ayoprez.iggy.library.activities.FullScreenImageGalleryActivity;
import com.ayoprez.iggy.library.activities.ImageGalleryActivity;
import com.ayoprez.iggy.library.adapters.FullScreenImageGalleryAdapter;
import com.ayoprez.iggy.library.adapters.ImageGalleryAdapter;
import com.ayoprez.iggy.library.enums.PaletteColorType;
import com.mikepenz.aboutlibraries.LibsBuilder;
import com.mikepenz.aboutlibraries.ui.LibsFragment;
import com.mikepenz.aboutlibraries.ui.LibsSupportFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;
import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, MainView, ImageGalleryAdapter.ImageThumbnailLoader, FullScreenImageGalleryAdapter.FullScreenImageLoader {

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
        ((CastroApplication)getApplication()).getComponent().inject(this);

        ImageGalleryFragment.setImageThumbnailLoader(this);
        FullScreenImageGalleryActivity.setFullScreenImageLoader(this);

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
                mainPresenter.initGallery(this);
                break;
            case R.id.nav_videos:
                changeFragment(VideosGalleryFragment.getInstance());
                break;
            case R.id.nav_sponsors:
                changeFragment(SponsorsFragment.getInstance());
                break;
            case R.id.nav_settings:
                changeFragment(new LibsBuilder()
                        .withAboutIconShown(true)
                        .withAboutAppName(getString(R.string.app_name))
                        .withAboutDescription(getString(R.string.settings_text) + " " + "<a href=\"http://www.ayoprez.com\">www.ayoprez.com</a>.")
                        .supportFragment());
                break;
            default:
                changeFragment(EventListFragment.getInstance());
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
    public void initImageGallery(ArrayList picturesUrl) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, picturesUrl);
        bundle.putBoolean(FullScreenImageGalleryActivity.KEY_DOWNLOAD_BUTTON, true);
        bundle.putBoolean(FullScreenImageGalleryActivity.KEY_SHARE_BUTTON, true);

        ImageGalleryFragment imageGalleryFragment = ImageGalleryFragment.newInstance(bundle);

        changeFragment(imageGalleryFragment);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        errorNotification.showNotification(this, getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }

    @Override
    protected void attachBaseContext(Context newBase) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase));
    }

    //Cambiar de sitio
    @Override
    public void loadImageThumbnail(ImageView iv, String imageUrl, int dimension) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(iv.getContext())
                    .load(imageUrl)
                    .resize(dimension, dimension)
                    .centerCrop()
                    .into(iv);
        } else {
            iv.setImageDrawable(null);
        }
    }

    //Cambiar de sitio
    @Override
    public void loadFullScreenImage(final ImageView iv, String imageUrl, int width, final LinearLayout bgLinearLayout) {
        if (!TextUtils.isEmpty(imageUrl)) {
            Picasso.with(iv.getContext())
                    .load(imageUrl)
                    .resize(width, 0)
                    .into(iv, new Callback() {
                        @Override
                        public void onSuccess() {
                            Bitmap bitmap = ((BitmapDrawable) iv.getDrawable()).getBitmap();
                            PaletteHelper.applyPalette(bitmap, PaletteColorType.VIBRANT, bgLinearLayout);
                        }

                        @Override
                        public void onError() {
                            errorNotification.showNotification(MainActivity.this, getResources().getStringArray(R.array.errorsArray)[ErrorManager.ERROR_NO_DATA_IMAGE]);
                        }
                    });
        } else {
            iv.setImageDrawable(null);
        }
    }
}
