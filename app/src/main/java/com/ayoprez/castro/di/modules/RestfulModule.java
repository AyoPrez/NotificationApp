package com.ayoprez.castro.di.modules;

import com.ayoprez.castro.model.repository.AboutUsRepository;
import com.ayoprez.castro.model.repository.ArenaRepository;
import com.ayoprez.castro.model.repository.EventsRepository;
import com.ayoprez.castro.model.repository.GamesRepository;
import com.ayoprez.castro.model.repository.ImagesGalleryRepository;
import com.ayoprez.castro.model.repository.PlayersRepository;
import com.ayoprez.castro.model.repository.SponsorRepository;
import com.ayoprez.castro.model.repository.VideosGalleryRepository;
import com.ayoprez.castro.model.restful.AboutUsRestfulService;
import com.ayoprez.castro.model.restful.AboutUsRestfulServiceImpl;
import com.ayoprez.castro.model.restful.ArenaRestfulService;
import com.ayoprez.castro.model.restful.ArenaRestfulServiceImpl;
import com.ayoprez.castro.model.restful.EventsRestfulService;
import com.ayoprez.castro.model.restful.EventsRestfulServiceImpl;
import com.ayoprez.castro.model.restful.GamesRestfulService;
import com.ayoprez.castro.model.restful.GamesRestfulServiceImpl;
import com.ayoprez.castro.model.restful.ImageRestfulService;
import com.ayoprez.castro.model.restful.ImageRestfulServiceImpl;
import com.ayoprez.castro.model.restful.PlayerRestfulService;
import com.ayoprez.castro.model.restful.PlayerRestfulServiceImpl;
import com.ayoprez.castro.model.restful.RestfulService;
import com.ayoprez.castro.model.restful.SponsorsRestfulService;
import com.ayoprez.castro.model.restful.SponsorsRestfulServiceImpl;
import com.ayoprez.castro.model.restful.VideoRestfulService;
import com.ayoprez.castro.model.restful.VideoRestfulServiceImpl;

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
