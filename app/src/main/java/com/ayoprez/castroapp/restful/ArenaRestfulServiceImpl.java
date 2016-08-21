package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.Arena;
import com.ayoprez.castroapp.repository.ArenaRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class ArenaRestfulServiceImpl implements ArenaRestfulService {
    private static final String TAG = ArenaRestfulServiceImpl.class.getSimpleName();

    private ArenaRepository repository;
    private RestfulService service;

    public ArenaRestfulServiceImpl(ArenaRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getArena() != null){
            repository.deleteArena();
        }
    }

    @Override
    public void getRestfulArena(final CommonActivityView view) {

        try {
            Response<ArrayList<Arena>> response = service.getArenaFromServer().execute();

            if (response.isSuccessful()) {
                repository.saveArena(response.body().get(0));
            }else{
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getArenaFromServer().enqueue(new Callback<ArrayList<Arena>>() {
//            @Override
//            public void onResponse(Call<ArrayList<Arena>> call, Response<ArrayList<Arena>> response) {
//                if(response.isSuccessful()) {
//                    repository.saveArena(response.body().get(0));
//                }else{
//                    view.showErrorMessage();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<Arena>> call, Throwable t) {
//                view.showErrorMessage();
//            }
//        });
    }
}
