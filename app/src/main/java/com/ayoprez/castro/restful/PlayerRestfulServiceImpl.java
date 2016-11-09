package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.repository.PlayersRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 20.08.16.
 */
public class PlayerRestfulServiceImpl extends ErrorManager implements PlayerRestfulService {
    private static final String TAG = PlayerRestfulServiceImpl.class.getSimpleName();

    private PlayersRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public PlayerRestfulServiceImpl(PlayersRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulPlayers(final CommonActivityView view) {

        subscription = service.getPlayersFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<PlayerItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompletePlayersData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_PLAYERS);
                    }

                    @Override
                    public void onNext(ArrayList<PlayerItem> players) {
                        repository.savePlayers(players);
                    }
                });
    }

    @Override
    public void deleteCompletePlayersData() {
        if(repository.getAllPlayers().size() > 0){
            repository.deleteAllPlayers();
        }
    }
}
