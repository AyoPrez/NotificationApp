package com.ayoprez.castroapp.presenter.adapters.players;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.ui.viewholders.players.PlayerListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerAdapterPresenterImpl implements PlayerAdapterPresenter {

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
            view.showError();
        }else{
            applyDisplayPlayers(item);
        }
    }

    private void applyDisplayPlayers(PlayerItem playerItem){
        if(!playerItem.getImage().isEmpty() && !playerItem.getName().isEmpty()){
            view.displayItemImage(playerItem.getImage());
            view.displayItemTitle(playerItem.getName());
        }else{
            view.showError();
        }
    }

    @Override
    public int getPlayersCountSize() {
        return repository.getAllPlayers().size();
    }
}
