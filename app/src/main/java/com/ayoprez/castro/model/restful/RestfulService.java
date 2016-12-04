package com.ayoprez.castro.model.restful;

import com.ayoprez.castro.model.models.AboutUs;
import com.ayoprez.castro.model.models.Arena;
import com.ayoprez.castro.model.models.CalendarItem;
import com.ayoprez.castro.model.models.EventItem;
import com.ayoprez.castro.model.models.ImageItem;
import com.ayoprez.castro.model.models.PlayerItem;
import com.ayoprez.castro.model.models.SponsorItem;
import com.ayoprez.castro.model.models.TableItem;
import com.ayoprez.castro.model.models.VideoItem;

import java.util.ArrayList;

import retrofit2.http.GET;
import rx.Observable;
import rx.Single;

/**
 * Created by ayo on 18.08.16.
 */
public interface RestfulService {

    @GET("/wp-json/posts?type=foto&filter[posts_per_page]=-1")
    Observable<ArrayList<ImageItem>> getImagesFromServer();

    @GET("/wp-json/posts?type=sobre-nosotros")
    Single<ArrayList<AboutUs>> getAboutUsFromServer();

    @GET("/wp-json/posts?type=terrero&filter[posts_per_page]=-1")
    Single<ArrayList<Arena>> getArenaFromServer();

    @GET("/wp-json/posts?type=luchada&filter[posts_per_page]=-1")
    Observable<ArrayList<CalendarItem>> getCalendarFromServer();

    @GET("/wp-json/posts?type=evento&filter[posts_per_page]=-1")
    Observable<ArrayList<EventItem>> getEventsFromServer();

    @GET("/wp-json/posts?type=luchadores&filter[posts_per_page]=-1")
    Observable<ArrayList<PlayerItem>> getPlayersFromServer();

    @GET("/wp-json/posts?type=patrocinador&filter[posts_per_page]=-1")
    Observable<ArrayList<SponsorItem>> getSponsorsFromServer();

    @GET("/wp-json/posts?type=clasificacion&filter[posts_per_page]=-1")
    Observable<ArrayList<TableItem>> getTableFromServer();

    @GET("/wp-json/posts?type=video&filter[posts_per_page]=-1")
    Observable<ArrayList<VideoItem>> getVideosFromServer();
}