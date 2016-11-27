package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.model.repository.AboutUsRepository;
import com.ayoprez.castro.model.repository.AboutUsRepositoryImpl;
import com.ayoprez.castro.model.repository.ArenaRepository;
import com.ayoprez.castro.model.repository.ArenaRepositoryImpl;
import com.ayoprez.castro.model.repository.EventsRepository;
import com.ayoprez.castro.model.repository.EventsRepositoryImpl;
import com.ayoprez.castro.model.repository.GamesRepository;
import com.ayoprez.castro.model.repository.GamesRepositoryImpl;
import com.ayoprez.castro.model.repository.ImagesGalleryRepository;
import com.ayoprez.castro.model.repository.ImagesGalleryRepositoryImpl;
import com.ayoprez.castro.model.repository.NotificationEventsRepository;
import com.ayoprez.castro.model.repository.NotificationEventsRepositoryImpl;
import com.ayoprez.castro.model.repository.PlayersRepository;
import com.ayoprez.castro.model.repository.PlayersRepositoryImpl;
import com.ayoprez.castro.model.repository.SponsorRepository;
import com.ayoprez.castro.model.repository.SponsorRepositoryImpl;
import com.ayoprez.castro.model.repository.VideosGalleryRepository;
import com.ayoprez.castro.model.repository.VideosGalleryRepositoryImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 23.09.16.
 */
@Module
public class RepositoryModule {

    @Singleton
    @Provides
    public EventsRepository provideEventRepository(){
        return new EventsRepositoryImpl();
    }

    @Singleton
    @Provides
    public ArenaRepository provideArenaRepository(){
        return new ArenaRepositoryImpl();
    }

    @Singleton
    @Provides
    public AboutUsRepository provideAboutUsRepository(){
        return new AboutUsRepositoryImpl();
    }

    @Singleton
    @Provides
    public PlayersRepository providePlayersRepository(){
        return new PlayersRepositoryImpl();
    }

    @Singleton
    @Provides
    public ImagesGalleryRepository provideImagesGalleryRepository(){
        return new ImagesGalleryRepositoryImpl();
    }

    @Singleton
    @Provides
    public SponsorRepository provideSponsorRepository(){
        return new SponsorRepositoryImpl();
    }

    @Singleton
    @Provides
    public GamesRepository provideGamesRepository(){
        return new GamesRepositoryImpl();
    }

    @Singleton
    @Provides
    public VideosGalleryRepository provideVideosGalleryRepository(){
        return new VideosGalleryRepositoryImpl();
    }

    @Singleton
    @Provides
    public NotificationEventsRepository provideNotificationEvents(){
        return new NotificationEventsRepositoryImpl();
    }

}
