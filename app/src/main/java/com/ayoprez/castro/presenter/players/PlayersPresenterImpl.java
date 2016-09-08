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
    private String tag;

    public PlayersPresenterImpl(PlayersRepository playersRepository){
        this.repository = playersRepository;
    }

    @Override
    public void setView(PlayersView view) {
        if(view == null){
            throw new ViewNotFoundException();
        }
        this.playersView = view;
    }

    @Override
    public void setTag(String category) {
        if(category == null || category.equals("")){
            throw new NullPointerException();
        }
        this.tag = category;
    }

    @Override
    public void initView(){
        if(playersView == null){
            throw new ViewNotFoundException();
        }
        if(tag == null || tag.equals("")){
            throw new NullPointerException();
        }

        if(repository.getAllPlayers().size() <= 0){
            showError(playersView, ERROR_EMPTY_PLAYERS);
        }else {
            switch (tag) {
                case "Senior":
                    playersView.initRecyclerView(repository.getSeniorPlayers());
                    break;

                case "Cadet":
                    playersView.initRecyclerView(repository.getCadetPlayers());
                    break;

                case "Junio":
                    playersView.initRecyclerView(repository.getJuniorPlayers());
                    break;
            }
        }
    }
}
