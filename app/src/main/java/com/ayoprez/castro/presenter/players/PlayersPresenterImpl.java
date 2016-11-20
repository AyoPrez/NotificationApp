package com.ayoprez.castro.presenter.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.model.models.PlayerItem;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castro.model.repository.PlayersRepository;
import com.ayoprez.castro.ui.fragments.players.PlayersView;
import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersPresenterImpl extends ErrorManager implements PlayersPresenter, PlayerAdapterPresenter{

    private PlayersView playersView;
    protected PlayersRepository repository;
    private String categoryTag;
    protected PlayerListItemView itemView;
    protected PlayerItem item;

    public PlayersPresenterImpl(PlayersRepository playersRepository){
        this.repository = playersRepository;
    }

    @Override
    public void setView(PlayersView view) {
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(playersView == null) {
            playersView = view;
        }
    }

    public void setCategoryTag(String category) {
        if(category == null || category.equals("")){
            throw new NullPointerException();
        }
        this.categoryTag = category;
    }

    @Override
    public void initView(){
        if(playersView == null){
            throw new ViewNotFoundException();
        }
        if(categoryTag == null || categoryTag.equals("")){
            throw new NullPointerException();
        }

        if(repository.getAllPlayers().size() <= 0){
            showError(playersView, ERROR_EMPTY_PLAYERS);
        }else {
            switch (categoryTag) {
                case "Senior":
                    playersView.initRecyclerView(repository.getSeniorPlayers());
                    break;

                case "Cadet":
                    playersView.initRecyclerView(repository.getCadetPlayers());
                    break;

                case "Junior":
                    playersView.initRecyclerView(repository.getJuniorPlayers());
                    break;
            }
        }
    }

    @Override
    public void setView(PlayerListItemView view) {
        this.itemView = view;
    }

    @Override
    public void loadPlayersData(ArrayList<PlayerItem> playerItems, int position) {
        if(itemView == null){
            throw new ViewNotFoundException();
        }

        if(playerItems == null || playerItems.size() == 0){
            showError(itemView, ERROR_EMPTY_PLAYERS, position);
        }else {
            item = playerItems.get(position);

            if (item == null) {
                showError(itemView, ERROR_EMPTY_PLAYERS, position);
            } else {
                applyDisplayPlayers(item, position);
            }
        }
    }

    private void applyDisplayPlayers(PlayerItem playerItem, int position){
        if(playerItem != null && playerItem.getMeta() != null && !playerItem.getMeta().getPhoto().isEmpty() && !playerItem.getTitle().isEmpty()){
            itemView.displayItemImage(playerItem.getMeta().getPhoto());
            itemView.displayItemTitle(playerItem.getTitle());
        }else{
            showError(itemView, ERROR_NO_DATA_PLAYER, position);
        }
    }

}
