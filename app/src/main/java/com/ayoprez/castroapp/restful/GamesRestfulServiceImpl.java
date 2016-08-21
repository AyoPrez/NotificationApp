package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.models.TableItem;
import com.ayoprez.castroapp.repository.GamesRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class GamesRestfulServiceImpl implements GamesRestfulService {
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
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }

//        service.getCalendarFromServer().enqueue(new Callback<ArrayList<CalendarItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<CalendarItem>> call, Response<ArrayList<CalendarItem>> response) {
//                if(response.isSuccessful()) {
//                    repository.saveCalendar(response.body().get(0));
//                }else {
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<CalendarItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });


        try {
            Response<ArrayList<TableItem>> response = service.getTableFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveTable(response.body().get(0));
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getTableFromServer().enqueue(new Callback<ArrayList<TableItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<TableItem>> call, Response<ArrayList<TableItem>> response) {
//                if(response.isSuccessful()) {
//                    repository.saveTable(response.body().get(0));
//                }else{
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<TableItem>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }

}
