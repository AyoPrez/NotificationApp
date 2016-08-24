package com.ayoprez.castro.presenter.adapters.players;

import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayerAdapterPresenter {

    void setView(PlayerListItemView view);
    void loadPlayersData();

    int getPlayersCountSize();
}
