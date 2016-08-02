package com.ayoprez.castroapp.presenter.players;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.ui.fragments.players.PlayersView;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersPresenterImpl implements PlayersPresenter{

    protected PlayersView playersView;
    protected PlayersRepository repository;

    public PlayersPresenterImpl(PlayersRepository playersRepository){
        this.repository = playersRepository;
    }

    @Override
    public void setView(PlayersView view) {
        this.playersView = view;
        initView();
    }

    private void initView(){
        if(playersView == null){
            throw new ViewNotFoundException();
        }

        if(repository.getAllPlayers().size() <= 0){
            playersView.showEmptyListMessage();
        }else {
            playersView.initRecyclerView();
        }
    }
}
