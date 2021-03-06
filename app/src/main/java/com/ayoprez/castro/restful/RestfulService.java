package com.ayoprez.castro.restful;

import com.ayoprez.castro.models.AboutUs;
import com.ayoprez.castro.models.Arena;
import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.ImageItem;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.models.TableItem;
import com.ayoprez.castro.models.VideoItem;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ayo on 18.08.16.
 */
public interface RestfulService {

    @GET("/wp-json/posts?type=foto&filter[posts_per_page]=-1")
    Call<ArrayList<ImageItem>> getImagesFromServer();

    @GET("/wp-json/posts?type=sobre-nosotros")
    Call<ArrayList<AboutUs>> getAboutUsFromServer();

    @GET("/wp-json/posts?type=terrero&filter[posts_per_page]=-1")
    Call<ArrayList<Arena>> getArenaFromServer();

    @GET("/wp-json/posts?type=luchada&filter[posts_per_page]=-1")
    Call<ArrayList<CalendarItem>> getCalendarFromServer();

    @GET("/wp-json/posts?type=evento&filter[posts_per_page]=-1")
    Call<ArrayList<EventItem>> getEventsFromServer();

    @GET("/wp-json/posts?type=luchadores&filter[posts_per_page]=-1")
    Call<ArrayList<PlayerItem>> getPlayersFromServer();

    @GET("/wp-json/posts?type=patrocinador&filter[posts_per_page]=-1")
    Call<ArrayList<SponsorItem>> getSponsorsFromServer();

    @GET("/wp-json/posts?type=clasificacion&filter[posts_per_page]=-1")
    Call<ArrayList<TableItem>> getTableFromServer();

    @GET("/wp-json/posts?type=video&filter[posts_per_page]=-1")
    Call<ArrayList<VideoItem>> getVideosFromServer();
}
