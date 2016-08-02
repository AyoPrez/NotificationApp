package com.ayoprez.castroapp.presenter.arena;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.Arena;
import com.ayoprez.castroapp.repository.ArenaRepository;
import com.ayoprez.castroapp.ui.fragments.arena.ArenaView;

/**
 * Created by ayo on 03.07.16.
 */
public class ArenaPresenterImpl implements ArenaPresenter {
    private static final String TAG = ArenaPresenterImpl.class.getSimpleName();

    private ArenaView view;
    private ArenaRepository arenaRepository;
    private Arena arena;

    public ArenaPresenterImpl(ArenaRepository repository) {
        this.arenaRepository = repository;
    }


    @Override
    public void loadArenaData() {
        arena = arenaRepository.getArena();

        if (arena == null) {
            view.showErrorMessage();
        } else {
            view.displayName(arena.getName());
            view.displayAddress(arena.getAddress());
            view.displayDescription(arena.getDescription());
            view.displayImage(arena.getImage());
        }
    }

    @Override
    public void setView(ArenaView view) {
        if (view == null) {
            throw new ViewNotFoundException();
        }
        this.view = view;
        loadArenaData();
    }

    @Override
    public void openMap(String address) {
        if (address == null || address.trim().equals("")) {
            view.showErrorMessage();
        } else {
            view.displayMap(address);
        }
    }
}
