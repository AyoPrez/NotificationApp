package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.VideoItem;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class VideoRestfulServiceImpl implements VideoRestfulService {
    private static final String TAG = VideoRestfulServiceImpl.class.getSimpleName();

    private VideosGalleryRepository repository;
    private RestfulService service;

    public VideoRestfulServiceImpl(VideosGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAllVideos().size() > 0){
            repository.deleteAllVideos();
        }
    }

    @Override
    public void getRestfulVideos(final CommonActivityView view) {
        try {
            Response<ArrayList<VideoItem>> response = service.getVideosFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveVideos(response.body());
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }

//        service.getVideosFromServer().enqueue(new Callback<ArrayList<VideoItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<VideoItem>> call, Response<ArrayList<VideoItem>> response) {
//                if (response.isSuccessful()) {
//                    repository.saveVideos(response.body());
//                }else {
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<VideoItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
