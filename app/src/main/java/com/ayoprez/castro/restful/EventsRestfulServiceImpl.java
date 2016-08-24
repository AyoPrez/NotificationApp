package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.repository.EventsRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class EventsRestfulServiceImpl extends ErrorManager implements EventsRestfulService {
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
                showError(view, ERROR_RESTFUL_EVENTS);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_EVENTS);
        }
    }
}
