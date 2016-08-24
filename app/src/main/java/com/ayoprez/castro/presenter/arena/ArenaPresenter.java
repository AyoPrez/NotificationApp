package com.ayoprez.castro.presenter.arena;

import com.ayoprez.castro.ui.fragments.arena.ArenaView;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaPresenter {

    void loadArenaData();
    void setView(ArenaView view);
    void openMap(String address);

}
