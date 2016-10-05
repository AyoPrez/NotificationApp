package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.Arena;
import com.ayoprez.castro.repository.ArenaRepository;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 19.08.16.
 */
public class ArenaRestfulServiceImpl extends ErrorManager implements ArenaRestfulService {
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
                showError(view, ERROR_RESTFUL_ARENA);
            }
        } catch (SocketTimeoutException stoe){
            Log.e(TAG, "Error: ", stoe);
            getRestfulArena(view);
        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);

            showError(view, ERROR_RESTFUL_ARENA);
        }
    }
}
