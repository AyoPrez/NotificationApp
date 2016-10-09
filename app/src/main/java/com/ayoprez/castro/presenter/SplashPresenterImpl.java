package com.ayoprez.castro.presenter;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import com.ayoprez.castro.di.AppComponent;
import com.ayoprez.castro.restful.AboutUsRestfulService;
import com.ayoprez.castro.restful.ArenaRestfulService;
import com.ayoprez.castro.restful.EventsRestfulService;
import com.ayoprez.castro.restful.GamesRestfulService;
import com.ayoprez.castro.restful.ImageRestfulService;
import com.ayoprez.castro.restful.PlayerRestfulService;
import com.ayoprez.castro.restful.SponsorsRestfulService;
import com.ayoprez.castro.restful.VideoRestfulService;
import com.ayoprez.castro.ui.SplashView;

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
    }

    @Override
    public void getDataFromServer(boolean isConnected, final boolean isWifi) {
        view.showLoadBar();

        if(isConnected) {

            Thread thread1 = new Thread() {
                @Override
                public void run() {
                    eventsRestfulService.getRestfulEvents(view);
                    gamesRestfulService.getRestfulGames(view);
                    imageRestfulService.getRestfulImages(view);
                    videoRestfulService.getRestfulVideos(view);
                }
            };

            thread1.start();

            if(isWifi) {
                Thread thread2 = new Thread() {
                    @Override
                    public void run() {
                        sponsorRestfulService.getRestfulSponsors(view);
                        playerRestfulService.getRestfulPlayers(view);
                        arenaRestfulService.getRestfulArena(view);
                        aboutUsRestfulService.getRestfulAboutUs(view);
                    }
                };
                thread2.start();
            }
        }

        Thread threadSplashWait = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(5000);
                } catch (InterruptedException e) {
                    Log.e(TAG, "Error sleeping: ", e);
                }
                view.changeActivity();
            }
        };

        threadSplashWait.start();
    }

}
