package com.ayoprez.castro.model.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.model.models.Arena;
import com.ayoprez.castro.model.repository.ArenaRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 19.08.16.
 */
public class ArenaRestfulServiceImpl extends ErrorManager implements ArenaRestfulService {
    private static final String TAG = ArenaRestfulServiceImpl.class.getSimpleName();

    private ArenaRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public ArenaRestfulServiceImpl(ArenaRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulArena(final CommonActivityView view) {

        subscription = service.getArenaFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<Arena>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompleteArenaData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_ARENA);
                    }

                    @Override
                    public void onNext(ArrayList<Arena> arena) {
                        repository.saveArena(arena.get(0));
                    }
                });
    }

    @Override
    public void deleteCompleteArenaData() {
        if(repository.getArena() != null){
            repository.deleteArena();
        }
    }
}
