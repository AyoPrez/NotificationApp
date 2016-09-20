package com.ayoprez.castro.presenter.arena;

import android.text.Html;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.Arena;
import com.ayoprez.castro.repository.ArenaRepository;
import com.ayoprez.castro.ui.fragments.arena.ArenaView;

/**
 * Created by ayo on 03.07.16.
 */
public class ArenaPresenterImpl extends ErrorManager implements ArenaPresenter {
    private static final String TAG = ArenaPresenterImpl.class.getSimpleName();

    protected ArenaView view;
    protected ArenaRepository arenaRepository;
    protected Arena arena;

    public ArenaPresenterImpl(ArenaRepository repository) {
        this.arenaRepository = repository;
    }


    @Override
    public void loadArenaData() {
        arena = arenaRepository.getArena();

        if (arena == null) {
            showError(view, ERROR_EMPTY_ARENA);
        } else {
            view.displayName(arena.getTitle());
            view.displayAddress(arena.getMeta().getCoordinates().getAddress());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                view.displayDescription(Html.fromHtml(arena.getMeta().getDescription(), Html.FROM_HTML_MODE_LEGACY).toString());
            } else {
                view.displayDescription(Html.fromHtml(arena.getMeta().getDescription()).toString());
            }

            view.displayImage(arena.getMeta().getPhoto());
            view.clickMapButton(arena.getMeta().getCoordinates().getAddress());
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
            showError(view, ERROR_NO_DATA_ARENA);
        } else {
            view.displayMap(address);
        }
    }
}
