package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.SponsorItem;
import com.ayoprez.castroapp.repository.SponsorRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class SponsorsRestfulServiceImpl implements SponsorsRestfulService {
    private static final String TAG = SponsorsRestfulServiceImpl.class.getSimpleName();

    private SponsorRepository repository;
    private RestfulService service;

    public SponsorsRestfulServiceImpl(SponsorRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAllSponsors().size() > 0){
            repository.deleteAllSponsors();
        }
    }

    @Override
    public void getRestfulSponsors(final CommonActivityView view) {
        try {
            Response<ArrayList<SponsorItem>> response = service.getSponsorsFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveSponsor(response.body());
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getSponsorsFromServer().enqueue(new Callback<ArrayList<SponsorItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<SponsorItem>> call, Response<ArrayList<SponsorItem>> response) {
//                if(response.isSuccessful()) {
//                    repository.saveSponsor(response.body());
//                }else{
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<SponsorItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
