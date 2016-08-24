package com.ayoprez.castro.restful;

import android.util.Log;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.repository.PlayersRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class PlayerRestfulServiceImpl extends ErrorManager implements PlayerRestfulService {
    private static final String TAG = PlayerRestfulServiceImpl.class.getSimpleName();

    private PlayersRepository repository;
    private RestfulService service;

    public PlayerRestfulServiceImpl(PlayersRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;

        if(repository.getAllPlayers().size() > 0){
            repository.deleteAllPlayers();
        }
    }

    @Override
    public void getRestfulPlayers(final CommonActivityView view) {
        try {
            Response<ArrayList<PlayerItem>> response = service.getPlayersFromServer().execute();

            if (response.isSuccessful()) {
                repository.savePlayers(response.body());
            }else{
                showError(view, ERROR_RESTFUL_PLAYERS);
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            showError(view, ERROR_RESTFUL_PLAYERS);
        }
    }
}
