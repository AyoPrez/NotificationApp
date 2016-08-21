package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.ui.SplashView;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class EventsRestfulServiceImpl implements EventsRestfulService {
    private static final String TAG = EventsRestfulServiceImpl.class.getSimpleName();

    private EventsRepository repository;
    private RestfulService service;

    public EventsRestfulServiceImpl(EventsRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAllEvents().size() > 0){
            repository.deleteAllEvents();
        }
    }

    @Override
    public void getRestfulEvents(final CommonActivityView view) {

        try {
            Response<ArrayList<EventItem>> response = service.getEventsFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveEvents(response.body());
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getEventsFromServer().enqueue(new Callback<ArrayList<EventItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<EventItem>> call, Response<ArrayList<EventItem>> response) {
//                if(response.isSuccessful()) {
//                    repository.saveEvents(response.body());
//                }else{
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<EventItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
