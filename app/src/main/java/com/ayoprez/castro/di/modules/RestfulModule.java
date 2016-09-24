package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.repository.ArenaRepository;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.restful.AboutUsRestfulService;
import com.ayoprez.castro.restful.AboutUsRestfulServiceImpl;
import com.ayoprez.castro.restful.ArenaRestfulService;
import com.ayoprez.castro.restful.ArenaRestfulServiceImpl;
import com.ayoprez.castro.restful.EventsRestfulService;
import com.ayoprez.castro.restful.EventsRestfulServiceImpl;
import com.ayoprez.castro.restful.GamesRestfulService;
import com.ayoprez.castro.restful.GamesRestfulServiceImpl;
import com.ayoprez.castro.restful.ImageRestfulService;
import com.ayoprez.castro.restful.ImageRestfulServiceImpl;
import com.ayoprez.castro.restful.PlayerRestfulService;
import com.ayoprez.castro.restful.PlayerRestfulServiceImpl;
import com.ayoprez.castro.restful.RestfulService;
import com.ayoprez.castro.restful.SponsorsRestfulService;
import com.ayoprez.castro.restful.SponsorsRestfulServiceImpl;
import com.ayoprez.castro.restful.VideoRestfulService;
import com.ayoprez.castro.restful.VideoRestfulServiceImpl;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ayo on 23.09.16.
 */
@Module
public class RestfulModule {

    //    Restful Services

    @Provides
    public EventsRestfulService provideEventsRestfulService(EventsRepository repository, RestfulService service){
        return new EventsRestfulServiceImpl(repository, service);
    }

    @Provides
    public PlayerRestfulService providePlayerRestfulService(PlayersRepository repository, RestfulService service){
        return new PlayerRestfulServiceImpl(repository, service);
    }

    @Provides
    public ArenaRestfulService provideArenaRestfulService(ArenaRepository repository, RestfulService service){
        return new ArenaRestfulServiceImpl(repository, service);
    }

    @Provides
    public AboutUsRestfulService provideAboutUsRestfulService(AboutUsRepository repository, RestfulService service){
        return new AboutUsRestfulServiceImpl(repository, service);
    }

    @Provides
    public GamesRestfulService provideGamesRestfulService(GamesRepository repository, RestfulService service){
        return new GamesRestfulServiceImpl(repository, service);
    }

    @Provides
    public ImageRestfulService provideImageRestfulService(ImagesGalleryRepository repository, RestfulService service){
        return new ImageRestfulServiceImpl(repository, service);
    }

    @Provides
    public SponsorsRestfulService provideSponsorRestfulService(SponsorRepository repository, RestfulService service){
        return new SponsorsRestfulServiceImpl(repository, service);
    }

    @Provides
    public VideoRestfulService provideVideoRestfulService(VideosGalleryRepository repository, RestfulService service){
        return new VideoRestfulServiceImpl(repository, service);
    }
}
