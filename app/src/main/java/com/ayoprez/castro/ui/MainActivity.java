package com.ayoprez.castro.ui;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.ayoprez.castro.CastroApplication;
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
import com.etiennelawlor.imagegallery.library.ImageGalleryFragment;
import com.etiennelawlor.imagegallery.library.activities.FullScreenImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.activities.ImageGalleryActivity;
import com.etiennelawlor.imagegallery.library.adapters.FullScreenImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.adapters.ImageGalleryAdapter;
import com.etiennelawlor.imagegallery.library.enums.PaletteColorType;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;

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

    private PaletteColorType paletteColorType;

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
//                changeFragment(ImagesGalleryFragment.getInstance());
                mainPresenter.initGallery(this);
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
    public void initImageGallery(ArrayList picturesUrl) {
        Bundle bundle = new Bundle();
        bundle.putStringArrayList(ImageGalleryActivity.KEY_IMAGES, picturesUrl);

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
                            Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
                                public void onGenerated(Palette palette) {
                                    applyPalette(palette, bgLinearLayout);
                                }
                            });
                        }

                        @Override
                        public void onError() {

                        }
                    });
        } else {
            iv.setImageDrawable(null);
        }
    }

    @Override
    public void imageShareOption(Uri image) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_STREAM, image);
        shareIntent.setType("image/jpeg");

        if (shareIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }

    @Override
    public void imageDownloadOption(String url) {

    }

    private void applyPalette(Palette palette, LinearLayout bgLinearLayout){
        int bgColor = getBackgroundColor(palette);
        if (bgColor != -1)
            bgLinearLayout.setBackgroundColor(bgColor);
    }

    private int getBackgroundColor(Palette palette) {
        int bgColor = -1;

        int vibrantColor = palette.getVibrantColor(0x000000);
        int lightVibrantColor = palette.getLightVibrantColor(0x000000);
        int darkVibrantColor = palette.getDarkVibrantColor(0x000000);

        int mutedColor = palette.getMutedColor(0x000000);
        int lightMutedColor = palette.getLightMutedColor(0x000000);
        int darkMutedColor = palette.getDarkMutedColor(0x000000);

        if (paletteColorType != null) {
            switch (paletteColorType) {
                case VIBRANT:
                    if (vibrantColor != 0) { // primary option
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) { // fallback options
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case LIGHT_VIBRANT:
                    if (lightVibrantColor != 0) { // primary option
                        bgColor = lightVibrantColor;
                    } else if (vibrantColor != 0) { // fallback options
                        bgColor = vibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case DARK_VIBRANT:
                    if (darkVibrantColor != 0) { // primary option
                        bgColor = darkVibrantColor;
                    } else if (vibrantColor != 0) { // fallback options
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (mutedColor != 0) {
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    }
                    break;
                case MUTED:
                    if (mutedColor != 0) { // primary option
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) { // fallback options
                        bgColor = lightMutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                case LIGHT_MUTED:
                    if (lightMutedColor != 0) { // primary option
                        bgColor = lightMutedColor;
                    } else if (mutedColor != 0) { // fallback options
                        bgColor = mutedColor;
                    } else if (darkMutedColor != 0) {
                        bgColor = darkMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                case DARK_MUTED:
                    if (darkMutedColor != 0) { // primary option
                        bgColor = darkMutedColor;
                    } else if (mutedColor != 0) { // fallback options
                        bgColor = mutedColor;
                    } else if (lightMutedColor != 0) {
                        bgColor = lightMutedColor;
                    } else if (vibrantColor != 0) {
                        bgColor = vibrantColor;
                    } else if (lightVibrantColor != 0) {
                        bgColor = lightVibrantColor;
                    } else if (darkVibrantColor != 0) {
                        bgColor = darkVibrantColor;
                    }
                    break;
                default:
                    break;
            }
        }

        return bgColor;
    }
}
