package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.AboutUs;
import com.ayoprez.castro.repository.AboutUsRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class AboutUsRestfulServiceImpl extends ErrorManager implements AboutUsRestfulService {
    private static final String TAG = AboutUsRestfulServiceImpl.class.getSimpleName();

    private AboutUsRepository repository;
    private RestfulService service;

    public AboutUsRestfulServiceImpl(AboutUsRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAboutUs() != null){
            repository.deleteAboutUs();
        }
    }

    @Override
    public void getRestfulAboutUs(final CommonActivityView view) {
        try {
            Response<ArrayList<AboutUs>> response = service.getAboutUsFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveAboutUs(response.body().get(0));
            }else{
                showError(view, ERROR_RESTFUL_ABOUTUS);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_ABOUTUS);
        }

    }
}
