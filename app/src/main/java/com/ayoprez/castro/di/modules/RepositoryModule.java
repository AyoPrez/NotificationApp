package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.models.NotificationEvents;
import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.repository.AboutUsRepositoryImpl;
import com.ayoprez.castro.repository.ArenaRepository;
import com.ayoprez.castro.repository.ArenaRepositoryImpl;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.EventsRepositoryImpl;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.repository.GamesRepositoryImpl;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.repository.ImagesGalleryRepositoryImpl;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepositoryImpl;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.repository.PlayersRepositoryImpl;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.repository.SponsorRepositoryImpl;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.repository.VideosGalleryRepositoryImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 23.09.16.
 */
@Module
public class RepositoryModule {

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
    public NotificationEventsRepository provideNotificationEvents(){
        return new NotificationEventsRepositoryImpl();
    }

}
