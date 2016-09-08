package com.ayoprez.castro;

import android.content.Context;

import com.ayoprez.castro.common.Constants;
import com.ayoprez.castro.presenter.SplashPresenter;
import com.ayoprez.castro.presenter.SplashPresenterImpl;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenterImpl;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenter;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenterImpl;
import com.ayoprez.castro.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.images.GalleryAdapterPresenterImpl;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenterImpl;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenter;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenterImpl;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenterImpl;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorsListAdapterPresenterImpl;
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
import com.ayoprez.castro.repository.AboutUsRepositoryImpl;
import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.repository.ArenaRepositoryImpl;
import com.ayoprez.castro.repository.ArenaRepository;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.repository.GamesRepositoryImpl;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.repository.ImagesGalleryRepositoryImpl;
import com.ayoprez.castro.repository.EventsRepositoryImpl;
import com.ayoprez.castro.repository.PlayersRepositoryImpl;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.repository.SponsorRepositoryImpl;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.repository.VideosGalleryRepositoryImpl;
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
import com.crashlytics.android.Crashlytics;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.fabric.sdk.android.Fabric;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

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
    public PlayerAdapterPresenter providePlayerAdapterPresenter(){
        return new PlayerAdapterPresenterImpl();
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

    @Provides
    public SplashPresenter provideSplashPresenter() {
        return new SplashPresenterImpl();
    }

    @Provides
    public OkHttpClient provideLoggingCapableHttpClient() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();

        logging.setLevel(BuildConfig.DEBUG ? HttpLoggingInterceptor.Level.BODY : HttpLoggingInterceptor.Level.NONE);

        return new OkHttpClient.Builder().addInterceptor(logging).build();
    }

    @Provides
    public Retrofit provideRetrofit(String baseURL) {
        return new Retrofit.Builder().baseUrl(baseURL).addConverterFactory(GsonConverterFactory.create()).client(provideLoggingCapableHttpClient()).build();
    }

    @Provides
    public RestfulService provideRestfulService(){
        return provideRetrofit(Constants.BASE_URL).create(RestfulService.class);
    }

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
