package com.ayoprez.castroapp.restful;

import android.util.Log;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.repository.PlayersRepository;

import java.io.IOException;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by ayo on 20.08.16.
 */
public class PlayerRestfulServiceImpl implements PlayerRestfulService {
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
                view.showErrorMessage();
            }

        } catch (IOException e) {
            Log.e(TAG, "Error: ", e);
            view.showErrorMessage();
        }


//        service.getPlayersFromServer().enqueue(new Callback<ArrayList<PlayerItem>>() {
//            @Override
//            public void onResponse(Call<ArrayList<PlayerItem>> call, Response<ArrayList<PlayerItem>> response) {
//                if(response.isSuccessful()) {
//                    repository.savePlayers(response.body());
//                }else{
//                    view.showErrorMessage();
//                }
//
//                repository.closeRealm();
//            }
//
//            @Override
//            public void onFailure(Call<ArrayList<PlayerItem>> call, Throwable t) {
//                view.showErrorMessage();
//
//                repository.closeRealm();
//            }
//        });
    }
}
