package com.ayoprez.castro.presenter.adapters.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerAdapterPresenterImpl extends ErrorManager implements PlayerAdapterPresenter {

    protected PlayerListItemView view;
    protected PlayerItem item;

    @Override
    public void setView(PlayerListItemView view) {
        this.view = view;
    }

    @Override
    public void loadPlayersData(ArrayList<PlayerItem> playerItems, int position) {
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(playerItems == null || playerItems.size() == 0){
            showError(view, ERROR_EMPTY_PLAYERS, position);
        }else {
            item = playerItems.get(position);

            if (item == null) {
                showError(view, ERROR_EMPTY_PLAYERS, position);
            } else {
                applyDisplayPlayers(item, position);
            }
        }
    }

    private void applyDisplayPlayers(PlayerItem playerItem, int position){
        if(playerItem != null && playerItem.getMeta() != null && !playerItem.getMeta().getPhoto().isEmpty() && !playerItem.getTitle().isEmpty()){
            view.displayItemImage(playerItem.getMeta().getPhoto());
            view.displayItemTitle(playerItem.getTitle());
        }else{
            showError(view, ERROR_NO_DATA_PLAYER, position);
        }
    }
}
