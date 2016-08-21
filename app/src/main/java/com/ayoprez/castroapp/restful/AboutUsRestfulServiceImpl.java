package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.repository.AboutUsRepository;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class AboutUsRestfulServiceImpl implements AboutUsRestfulService {
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
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getAboutUsFromServer().enqueue(new Callback<ArrayList<AboutUs>>() {
//            @Override
//            public void onResponse(Call<ArrayList<AboutUs>> call, Response<ArrayList<AboutUs>> response) {
//                if (response.isSuccessful()) {
//                    repository.saveAboutUs(response.body().get(0));
//                }else{
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<AboutUs>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
