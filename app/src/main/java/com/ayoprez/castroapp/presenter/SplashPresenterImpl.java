package com.ayoprez.castroapp.presenter;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.di.AppComponent;
import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.restful.AboutUsRestfulService;
import com.ayoprez.castroapp.restful.ArenaRestfulService;
import com.ayoprez.castroapp.restful.EventsRestfulService;
import com.ayoprez.castroapp.restful.GamesRestfulService;
import com.ayoprez.castroapp.restful.ImageRestfulService;
import com.ayoprez.castroapp.restful.PlayerRestfulService;
import com.ayoprez.castroapp.restful.SponsorsRestfulService;
import com.ayoprez.castroapp.restful.VideoRestfulService;
import com.ayoprez.castroapp.ui.SplashView;

import javax.inject.Inject;

/**
 * Created by ayo on 20.08.16.
 */
public class SplashPresenterImpl implements SplashPresenter {
    private static final String TAG = SplashPresenterImpl.class.getSimpleName();

    private SplashView view;

    @Inject
    PlayerRestfulService playerRestfulService;

    @Inject
    ArenaRestfulService arenaRestfulService;

    @Inject
    AboutUsRestfulService aboutUsRestfulService;

    @Inject
    EventsRestfulService eventsRestfulService;

    @Inject
    ImageRestfulService imageRestfulService;

    @Inject
    VideoRestfulService videoRestfulService;

    @Inject
    SponsorsRestfulService sponsorRestfulService;

    @Inject
    GamesRestfulService gamesRestfulService;

    @Override
    public void setView(AppComponent component, SplashView view) {
        this.view = view;
        component.inject(this);

        getDataFromServer();
    }

    @Override
    public void getDataFromServer() {
        view.showLoadBar();

        Thread thread = new Thread() {
            @Override
            public void run() {
                eventsRestfulService.getRestfulEvents(view);
                playerRestfulService.getRestfulPlayers(view);
                arenaRestfulService.getRestfulArena(view);
                aboutUsRestfulService.getRestfulAboutUs(view);
                imageRestfulService.getRestfulImages(view);
                videoRestfulService.getRestfulVideos(view);
                sponsorRestfulService.getRestfulSponsors(view);
                gamesRestfulService.getRestfulGames(view);
            }
        };

        thread.start();

        view.changeActivity();
    }

}
