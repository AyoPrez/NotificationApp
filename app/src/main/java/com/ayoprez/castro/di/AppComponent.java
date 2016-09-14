package com.ayoprez.castro.di;

import android.app.Activity;
import android.support.v4.app.Fragment;

import com.ayoprez.castro.AppModule;
import com.ayoprez.castro.presenter.SplashPresenterImpl;
import com.ayoprez.castro.ui.SplashActivity;
import com.ayoprez.castro.ui.adapters.EventsListAdapter;
import com.ayoprez.castro.ui.adapters.ImagesGalleryListAdapter;
import com.ayoprez.castro.ui.adapters.PlayersListAdapter;
import com.ayoprez.castro.ui.adapters.SponsorsListAdapter;
import com.ayoprez.castro.ui.adapters.VideosGalleryListAdapter;
import com.ayoprez.castro.ui.fragments.ImageFragment;
import com.ayoprez.castro.ui.fragments.events.EventFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersCadetFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersJuniorFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersSeniorFragment;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsFragment;
import com.ayoprez.castro.ui.fragments.aboutus.AboutUsFragment;
import com.ayoprez.castro.ui.fragments.arena.ArenaFragment;
import com.ayoprez.castro.ui.fragments.events.EventListFragment;
import com.ayoprez.castro.ui.fragments.games.GamesCalendarFragment;
import com.ayoprez.castro.ui.fragments.games.GamesTableFragment;
import com.ayoprez.castro.ui.fragments.images.ImagesGalleryFragment;
import com.ayoprez.castro.ui.fragments.players.PlayersFragment;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryFragment;
import com.ayoprez.castro.ui.viewholders.events.EventsViewHolder;
import com.ayoprez.castro.ui.viewholders.images.GalleryItemViewHolder;
import com.ayoprez.castro.ui.viewholders.players.PlayersViewHolder;
import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemViewHolder;
import com.ayoprez.castro.ui.viewholders.videos.VideosGalleryItemViewHolder;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ayo on 19.06.16.
 */
@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    void inject(EventListFragment eventFragment);
    void inject(ArenaFragment arenaFragment);
    void inject(AboutUsFragment aboutUsFragment);
    void inject(EventsListAdapter eventsListAdapter);
    void inject(Fragment fragment);
    void inject(Activity activity);
    void inject(PlayersFragment playerFragment);
    void inject(PlayersSeniorFragment playersSeniorFragment);
    void inject(PlayersCadetFragment playersCadetFragment);
    void inject(PlayersJuniorFragment playersJuniorFragment);
    void inject(PlayersListAdapter playersListAdapter);
    void inject(ImagesGalleryFragment imagesGalleryFragment);
    void inject(ImagesGalleryListAdapter imagesGalleryListAdapter);
    void inject(SponsorsFragment sponsorsFragment);
    void inject(SponsorsListAdapter sponsorsListAdapter);
    void inject(GamesCalendarFragment gamesCalendarFragment);
    void inject(GamesTableFragment gamesTableFragment);
    void inject(VideosGalleryFragment videosGalleryFragment);
    void inject(VideosGalleryListAdapter videosGalleryListAdapter);
    void inject(SplashActivity splashActivity);
    void inject(EventFragment eventFragment);
    void inject(ImageFragment imageFragment);

//    Restful service
    void inject(SplashPresenterImpl splashPresenter);

    //ViewHolders
    void inject(SponsorItemViewHolder sponsorItemViewHolder);
    void inject(EventsViewHolder eventsViewHolder);
    void inject(VideosGalleryItemViewHolder videosGalleryItemViewHolder);
    void inject(GalleryItemViewHolder galleryItemViewHolder);
    void inject(PlayersViewHolder playersViewHolder);
}