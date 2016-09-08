package com.ayoprez.castro.presenter.adapters.players;

import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayerAdapterPresenter {

    void setView(PlayerListItemView view);
    void loadPlayersData(ArrayList<PlayerItem> playerItems, int position);
}
