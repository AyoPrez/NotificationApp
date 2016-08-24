package com.ayoprez.castro.presenter.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.ui.fragments.players.PlayersView;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersPresenterImpl extends ErrorManager implements PlayersPresenter{

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
            showError(playersView, ERROR_EMPTY_PLAYERS);
        }else {
            playersView.initRecyclerView();
        }
    }
}
