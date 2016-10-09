package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.ImageItem;
import com.ayoprez.castro.repository.ImagesGalleryRepository;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class ImageRestfulServiceImpl extends ErrorManager implements ImageRestfulService {
    private static final String TAG = ImageRestfulServiceImpl.class.getSimpleName();

    private ImagesGalleryRepository repository;
    private RestfulService service;

    public ImageRestfulServiceImpl(ImagesGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulImages(final CommonActivityView view) {

        deleteCompleteImagesData();

        try {
            Response<ArrayList<ImageItem>> response = service.getImagesFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveImages(response.body());
            }else{
                showError(view, ERROR_RESTFUL_IMAGES);
            }
        } catch (SocketTimeoutException stoe){
            Log.e(TAG, "Error: ", stoe);
            getRestfulImages(view);
        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_IMAGES);
        }
    }

    @Override
    public void deleteCompleteImagesData() {
        if(repository.getAllStringImages().size() > 0){
            repository.deleteAllImages();
        }
    }
}
