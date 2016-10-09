package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.repository.VideosGalleryRepository;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class VideoRestfulServiceImpl extends ErrorManager implements VideoRestfulService {
    private static final String TAG = VideoRestfulServiceImpl.class.getSimpleName();

    private VideosGalleryRepository repository;
    private RestfulService service;

    public VideoRestfulServiceImpl(VideosGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulVideos(final CommonActivityView view) {

        deleteCompleteVideoData();

        try {
            Response<ArrayList<VideoItem>> response = service.getVideosFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveVideos(response.body());
            }else{
                showError(view, ERROR_RESTFUL_VIDEOS);
            }
        } catch (SocketTimeoutException stoe){
            Log.e(TAG, "Error: ", stoe);
            getRestfulVideos(view);
        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_VIDEOS);
        }
    }

    @Override
    public void deleteCompleteVideoData() {
        if(repository.getAllVideos().size() > 0){
            repository.deleteAllVideos();
        }
    }
}
