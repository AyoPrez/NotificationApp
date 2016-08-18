package com.ayoprez.castroapp.di;

import com.ayoprez.castroapp.AppModule;
import com.ayoprez.castroapp.ui.adapters.EventsListAdapter;
import com.ayoprez.castroapp.ui.adapters.ImagesGalleryListAdapter;
import com.ayoprez.castroapp.ui.adapters.PlayersListAdapter;
import com.ayoprez.castroapp.ui.adapters.SponsorsListAdapter;
import com.ayoprez.castroapp.ui.adapters.VideosGalleryListAdapter;
import com.ayoprez.castroapp.ui.fragments.SponsorsFragment;
import com.ayoprez.castroapp.ui.fragments.aboutus.AboutUsFragment;
import com.ayoprez.castroapp.ui.fragments.arena.ArenaFragment;
import com.ayoprez.castroapp.ui.fragments.events.EventFragment;
import com.ayoprez.castroapp.ui.fragments.games.GamesCalendarFragment;
import com.ayoprez.castroapp.ui.fragments.games.GamesFragment;
import com.ayoprez.castroapp.ui.fragments.games.GamesTableFragment;
import com.ayoprez.castroapp.ui.fragments.images.ImagesGalleryFragment;
import com.ayoprez.castroapp.ui.fragments.players.PlayersFragment;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryFragment;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by ayo on 19.06.16.
 */
@Singleton
@Component(modules = { AppModule.class })
public interface AppComponent {
    void inject(EventFragment eventFragment);
    void inject(ArenaFragment arenaFragment);
    void inject(AboutUsFragment aboutUsFragment);
    void inject(EventsListAdapter eventsListAdapter);
    void inject(PlayersFragment playerFragment);
    void inject(PlayersListAdapter playersListAdapter);
    void inject(ImagesGalleryFragment imagesGalleryFragment);
    void inject(ImagesGalleryListAdapter imagesGalleryListAdapter);
    void inject(SponsorsFragment sponsorsFragment);
    void inject(SponsorsListAdapter sponsorsListAdapter);
    void inject(GamesCalendarFragment gamesCalendarFragment);
    void inject(GamesTableFragment gamesTableFragment);
    void inject(VideosGalleryFragment videosGalleryFragment);
    void inject(VideosGalleryListAdapter videosGalleryListAdapter);
}