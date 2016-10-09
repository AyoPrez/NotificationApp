package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.repository.SponsorRepository;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class SponsorsRestfulServiceImpl extends ErrorManager implements SponsorsRestfulService {
    private static final String TAG = SponsorsRestfulServiceImpl.class.getSimpleName();

    private SponsorRepository repository;
    private RestfulService service;

    public SponsorsRestfulServiceImpl(SponsorRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulSponsors(final CommonActivityView view) {

        deleteCompleteSponsorsData();

        try {
            Response<ArrayList<SponsorItem>> response = service.getSponsorsFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveSponsor(response.body());
            }else{

                showError(view, ERROR_RESTFUL_SPONSORS);
            }
        } catch (SocketTimeoutException stoe){
            Log.e(TAG, "Error: ", stoe);
            getRestfulSponsors(view);
        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_SPONSORS);
        }
    }

    @Override
    public void deleteCompleteSponsorsData() {
        if(repository.getAllSponsors().size() > 0){
            repository.deleteAllSponsors();
        }
    }
}
