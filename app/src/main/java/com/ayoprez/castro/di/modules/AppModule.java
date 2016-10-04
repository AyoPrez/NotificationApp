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
import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.repository.ArenaRepository;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.repository.VideosGalleryRepository;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 19.06.16.
 */
@Module
public class AppModule {

    @Provides
    public MainPresenter provideMainPresenter(ImagesGalleryRepository imagesGalleryRepository){
        return new MainPresenterImpl(imagesGalleryRepository);
    }

    @Provides
    public EventPresenter provideEventPresenter(EventsRepository eventsRepository, NotificationEventsRepository notificationEventsRepository){
        return new EventPresenterImpl(eventsRepository, notificationEventsRepository);
    }

    @Provides
    public ArenaPresenter provideArenaPresenter(ArenaRepository arenaRepository){
        return new ArenaPresenterImpl(arenaRepository);
    }

    @Provides
    public AboutUsPresenter provideAboutUsPresenter(AboutUsRepository aboutUsRepository){
        return new AboutUsPresenterImpl(aboutUsRepository);
    }

    @Provides
    public EventAdapterPresenter provideEventAdapterPresenter(EventsRepository eventsRepository, NotificationEventsRepository notificationEventsRepository){
        return new EventPresenterImpl(eventsRepository, notificationEventsRepository);
    }

    @Provides
    public PlayersPresenter providePlayersPresenter(PlayersRepository repository){
        return new PlayersPresenterImpl(repository);
    }

    @Provides
    public PlayerAdapterPresenter providePlayerAdapterPresenter(PlayersRepository repository){
        return new PlayersPresenterImpl(repository);
    }

    @Provides
    public GalleryPresenter provideGalleryPresenter(ImagesGalleryRepository repository){
        return new GalleryPresenterImpl(repository);
    }

    @Provides
    public GalleryAdapterPresenter provideGalleryAdapterPresenter(ImagesGalleryRepository repository){
        return new GalleryPresenterImpl(repository);
    }

    @Provides
    public SponsorsPresenter provideSponsorsPresenter(SponsorRepository repository){
        return new SponsorsPresenterImpl(repository);
    }

    @Provides
    public SponsorListAdapterPresenter provideSponsorListAdapterPresenter(SponsorRepository repository){
        return new SponsorsPresenterImpl(repository);
    }

    @Provides
    public GamesCalendarPresenter provideGamesCalendarPresenter(GamesRepository repository){
        return new GamesCalendarPresenterImpl(repository);
    }

    @Provides
    public GamesTablePresenter provideGamesTablePresenter(GamesRepository repository){
        return new GamesTablePresenterImpl(repository);
    }

    @Provides
    public VideosGalleryPresenter provideVideosGalleryPresenter(VideosGalleryRepository videosGalleryRepository){
        return new VideosGalleryPresenterImpl(videosGalleryRepository);
    }

    @Provides
    public VideoGalleryAdapterPresenter provideVideoGalleryAdapterPresenter(VideosGalleryRepository videosGalleryRepository){
        return new VideosGalleryPresenterImpl(videosGalleryRepository);
    }

    @Provides
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenterImpl();
    }
}
