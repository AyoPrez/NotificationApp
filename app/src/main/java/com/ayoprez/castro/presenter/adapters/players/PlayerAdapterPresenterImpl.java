package com.ayoprez.castro.presenter.adapters.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerAdapterPresenterImpl extends ErrorManager implements PlayerAdapterPresenter {

    protected PlayersRepository repository;
    protected PlayerListItemView view;
    protected PlayerItem item;


    public PlayerAdapterPresenterImpl(PlayersRepository playersRepository) {
        this.repository = playersRepository;
    }

    @Override
    public void setView(PlayerListItemView view) {
        this.view = view;
        loadPlayersData();
    }

    @Override
    public void loadPlayersData() {
        if(view == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = view.getItemPosition();
        item = repository.getPlayer(itemPosition);

        if(item == null){
            showError(view, ERROR_EMPTY_PLAYERS, itemPosition);
        }else{
            applyDisplayPlayers(item);
        }
    }

    private void applyDisplayPlayers(PlayerItem playerItem){
        if(playerItem != null && playerItem.getMeta() != null && !playerItem.getMeta().getPhoto().isEmpty() && !playerItem.getTitle().isEmpty()){
            view.displayItemImage(playerItem.getMeta().getPhoto());
            view.displayItemTitle(playerItem.getTitle());
        }else{
            showError(view, ERROR_NO_DATA_PLAYER, view.getItemPosition());
        }
    }

    @Override
    public int getPlayersCountSize() {
        return repository.getAllPlayers().size();
    }
}
