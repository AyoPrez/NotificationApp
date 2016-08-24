package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.models.TableItem;
import com.ayoprez.castro.repository.GamesRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class GamesRestfulServiceImpl extends ErrorManager implements GamesRestfulService {
    private static final String TAG = GamesRestfulServiceImpl.class.getSimpleName();

    private GamesRepository repository;
    private RestfulService service;

    public GamesRestfulServiceImpl(GamesRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getCalendar() != null){
            repository.deleteCalendar();
        }

        if(repository.getTable() != null){
            repository.deleteTable();
        }
    }

    @Override
    public void getRestfulGames(final CommonActivityView view) {
        try {
            Response<ArrayList<CalendarItem>> response = service.getCalendarFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveCalendar(response.body().get(0));
            }else{
                showError(view, ERROR_RESTFUL_GAMES);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_GAMES);
        }

        try {
            Response<ArrayList<TableItem>> response = service.getTableFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveTable(response.body().get(0));
            }else{
                showError(view, ERROR_RESTFUL_GAMES);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_GAMES);
        }
    }

}
