package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.presenter.MainPresenter;
import com.ayoprez.castro.presenter.MainPresenterImpl;
import com.ayoprez.castro.presenter.SplashPresenter;
import com.ayoprez.castro.presenter.SplashPresenterImpl;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenter;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenterImpl;
import com.ayoprez.castro.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenter;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenterImpl;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castro.presenter.arena.ArenaPresenter;
import com.ayoprez.castro.presenter.arena.ArenaPresenterImpl;
import com.ayoprez.castro.presenter.events.EventPresenter;
import com.ayoprez.castro.presenter.events.EventPresenterImpl;
import com.ayoprez.castro.presenter.games.GamesTablePresenter;
import com.ayoprez.castro.presenter.games.GamesTablePresenterImpl;
import com.ayoprez.castro.presenter.images.GalleryPresenter;
import com.ayoprez.castro.presenter.images.GalleryPresenterImpl;
import com.ayoprez.castro.presenter.players.PlayersPresenter;
import com.ayoprez.castro.presenter.players.PlayersPresenterImpl;
import com.ayoprez.castro.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castro.presenter.sponsors.SponsorsPresenterImpl;
import com.ayoprez.castro.presenter.videos.VideosGalleryPresenter;
import com.ayoprez.castro.presenter.videos.VideosGalleryPresenterImpl;
import com.ayoprez.castro.model.repository.AboutUsRepository;
import com.ayoprez.castro.model.repository.ArenaRepository;
import com.ayoprez.castro.model.repository.EventsRepository;
import com.ayoprez.castro.model.repository.GamesRepository;
import com.ayoprez.castro.model.repository.ImagesGalleryRepository;
import com.ayoprez.castro.model.repository.NotificationEventsRepository;
import com.ayoprez.castro.model.repository.PlayersRepository;
import com.ayoprez.castro.model.repository.SponsorRepository;
import com.ayoprez.castro.model.repository.VideosGalleryRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 19.06.16.
 */
@Module
public class AppModule {

    @Singleton
    @Provides
    public MainPresenter provideMainPresenter(ImagesGalleryRepository imagesGalleryRepository){
        return new MainPresenterImpl(imagesGalleryRepository);
    }

    @Singleton
    @Provides
    public EventPresenter provideEventPresenter(EventsRepository eventsRepository, NotificationEventsRepository notificationEventsRepository){
        return new EventPresenterImpl(eventsRepository, notificationEventsRepository);
    }

    @Singleton
    @Provides
    public ArenaPresenter provideArenaPresenter(ArenaRepository arenaRepository){
        return new ArenaPresenterImpl(arenaRepository);
    }

    @Singleton
    @Provides
    public AboutUsPresenter provideAboutUsPresenter(AboutUsRepository aboutUsRepository){
        return new AboutUsPresenterImpl(aboutUsRepository);
    }

    @Singleton
    @Provides
    public EventAdapterPresenter provideEventAdapterPresenter(EventsRepository eventsRepository, NotificationEventsRepository notificationEventsRepository){
        return new EventPresenterImpl(eventsRepository, notificationEventsRepository);
    }

    @Singleton
    @Provides
    public PlayersPresenter providePlayersPresenter(PlayersRepository repository){
        return new PlayersPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public PlayerAdapterPresenter providePlayerAdapterPresenter(PlayersRepository repository){
        return new PlayersPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public GalleryPresenter provideGalleryPresenter(ImagesGalleryRepository repository){
        return new GalleryPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public GalleryAdapterPresenter provideGalleryAdapterPresenter(ImagesGalleryRepository repository){
        return new GalleryPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public SponsorsPresenter provideSponsorsPresenter(SponsorRepository repository){
        return new SponsorsPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public SponsorListAdapterPresenter provideSponsorListAdapterPresenter(SponsorRepository repository){
        return new SponsorsPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public GamesCalendarPresenter provideGamesCalendarPresenter(GamesRepository repository){
        return new GamesCalendarPresenterImpl(repository);
    }

    @Singleton
    @Provides
    public GamesTablePresenter provideGamesTablePresenter(GamesRepository repository){
        return new GamesTablePresenterImpl(repository);
    }

    @Singleton
    @Provides
    public VideosGalleryPresenter provideVideosGalleryPresenter(VideosGalleryRepository videosGalleryRepository){
        return new VideosGalleryPresenterImpl(videosGalleryRepository);
    }

    @Singleton
    @Provides
    public VideoGalleryAdapterPresenter provideVideoGalleryAdapterPresenter(VideosGalleryRepository videosGalleryRepository){
        return new VideosGalleryPresenterImpl(videosGalleryRepository);
    }

    @Singleton
    @Provides
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenterImpl();
    }
}
