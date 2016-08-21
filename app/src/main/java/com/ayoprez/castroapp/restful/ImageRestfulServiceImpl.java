package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class ImageRestfulServiceImpl implements ImageRestfulService {
    private static final String TAG = ImageRestfulServiceImpl.class.getSimpleName();

    private ImagesGalleryRepository repository;
    private RestfulService service;

    public ImageRestfulServiceImpl(ImagesGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAllImages().size() > 0){
            repository.deleteAllImages();
        }
    }

    @Override
    public void getRestfulImages(final CommonActivityView view) {
        try {
            Response<ArrayList<ImageItem>> response = service.getImagesFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveImages(response.body());
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }

//        service.getImagesFromServer().enqueue(new Callback<ArrayList<ImageItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<ImageItem>> call, Response<ArrayList<ImageItem>> response) {
//                if(response.isSuccessful()) {
//                    if(response.isSuccessful()) {
//                        repository.saveImages(response.body());
//                    }else{
//                        view.showErrorMessage();
//                    }
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<ImageItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
