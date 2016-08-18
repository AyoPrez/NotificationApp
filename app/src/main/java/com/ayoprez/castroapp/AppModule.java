package com.ayoprez.castroapp;

import com.ayoprez.castroapp.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.videos.VideoGalleryAdapterPresenterImpl;
import com.ayoprez.castroapp.presenter.games.GamesCalendarPresenter;
import com.ayoprez.castroapp.presenter.games.GamesCalendarPresenterImpl;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenterImpl;
import com.ayoprez.castroapp.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.events.EventAdapterPresenterImpl;
import com.ayoprez.castroapp.presenter.aboutUs.AboutUsPresenter;
import com.ayoprez.castroapp.presenter.aboutUs.AboutUsPresenterImpl;
import com.ayoprez.castroapp.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.players.PlayerAdapterPresenterImpl;
import com.ayoprez.castroapp.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.sponsors.SponsorsListAdapterPresenterImpl;
import com.ayoprez.castroapp.presenter.arena.ArenaPresenter;
import com.ayoprez.castroapp.presenter.arena.ArenaPresenterImpl;
import com.ayoprez.castroapp.presenter.events.EventPresenter;
import com.ayoprez.castroapp.presenter.events.EventPresenterImpl;
import com.ayoprez.castroapp.presenter.games.GamesTablePresenter;
import com.ayoprez.castroapp.presenter.games.GamesTablePresenterImpl;
import com.ayoprez.castroapp.presenter.images.GalleryPresenter;
import com.ayoprez.castroapp.presenter.images.GalleryPresenterImpl;
import com.ayoprez.castroapp.presenter.players.PlayersPresenter;
import com.ayoprez.castroapp.presenter.players.PlayersPresenterImpl;
import com.ayoprez.castroapp.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castroapp.presenter.sponsors.SponsorsPresenterImpl;
import com.ayoprez.castroapp.presenter.videos.VideosGalleryPresenter;
import com.ayoprez.castroapp.presenter.videos.VideosGalleryPresenterImpl;
import com.ayoprez.castroapp.repository.AboutUsRepositoryImpl;
import com.ayoprez.castroapp.repository.AboutUsRepository;
import com.ayoprez.castroapp.repository.ArenaRepositoryImpl;
import com.ayoprez.castroapp.repository.ArenaRepository;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.repository.GamesRepository;
import com.ayoprez.castroapp.repository.GamesRepositoryImpl;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;
import com.ayoprez.castroapp.repository.ImagesGalleryRepositoryImpl;
import com.ayoprez.castroapp.repository.EventsRepositoryImpl;
import com.ayoprez.castroapp.repository.PlayersRepositoryImpl;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.repository.SponsorRepository;
import com.ayoprez.castroapp.repository.SponsorRepositoryImpl;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;
import com.ayoprez.castroapp.repository.VideosGalleryRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 19.06.16.
 */
@Module
public class AppModule {

    @Provides
    public EventsRepository provideEventRepository(){
        return new EventsRepositoryImpl();
    }

    @Provides
    public ArenaRepository provideArenaRepository(){
        return new ArenaRepositoryImpl();
    }

    @Provides
    public AboutUsRepository provideAboutUsRepository(){
        return new AboutUsRepositoryImpl();
    }

    @Provides
    public PlayersRepository providePlayersRepository(){
        return new PlayersRepositoryImpl();
    }

    @Provides
    public ImagesGalleryRepository provideImagesGalleryRepository(){
        return new ImagesGalleryRepositoryImpl();
    }

    @Provides
    public SponsorRepository provideSponsorRepository(){
        return new SponsorRepositoryImpl();
    }

    @Provides
    public GamesRepository provideGamesRepository(){
        return new GamesRepositoryImpl();
    }

    @Provides
    public VideosGalleryRepository provideVideosGalleryRepository(){
        return new VideosGalleryRepositoryImpl();
    }

    @Provides
    public EventPresenter provideEventPresenter(){
        return new EventPresenterImpl();
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
    public EventAdapterPresenter provideEventAdapterPresenter(EventsRepository eventsRepository){
        return new EventAdapterPresenterImpl(eventsRepository);
    }

    @Provides
    public PlayersPresenter providePlayersPresenter(PlayersRepository repository){
        return new PlayersPresenterImpl(repository);
    }

    @Provides
    public PlayerAdapterPresenter providePlayerAdapterPresenter(PlayersRepository playersRepository){
        return new PlayerAdapterPresenterImpl(playersRepository);
    }

    @Provides
    public GalleryPresenter provideGalleryPresenter(ImagesGalleryRepository repository){
        return new GalleryPresenterImpl(repository);
    }

    @Provides
    public GalleryAdapterPresenter provideGalleryAdapterPresenter(ImagesGalleryRepository repository){
        return new GalleryAdapterPresenterImpl(repository);
    }

    @Provides
    public SponsorsPresenter provideSponsorsPresenter(SponsorRepository repository){
        return new SponsorsPresenterImpl(repository);
    }

    @Provides
    public SponsorListAdapterPresenter provideSponsorListAdapterPresenter(SponsorRepository repository){
        return new SponsorsListAdapterPresenterImpl(repository);
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
        return new VideoGalleryAdapterPresenterImpl(videosGalleryRepository);
    }
}
