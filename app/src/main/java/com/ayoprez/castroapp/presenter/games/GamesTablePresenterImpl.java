package com.ayoprez.castroapp.presenter.games;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.TableItem;
import com.ayoprez.castroapp.repository.GamesRepository;
import com.ayoprez.castroapp.ui.fragments.games.GamesTableView;

/**
 * Created by ayo on 17.08.16.
 */
public class GamesTablePresenterImpl implements GamesTablePresenter {
    private static final String TAG = GamesTablePresenterImpl.class.getSimpleName();

    private GamesRepository repository;
    protected TableItem table;

    public GamesTablePresenterImpl(GamesRepository gamesRepository){
        this.repository = gamesRepository;
    }

    @Override
    public void setView(GamesTableView view) {
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(repository == null){
            view.showErrorMessage();
        }

        table = repository.getTable();

        if(table == null || table.getImage() == null || table.getImage().equals("")){
            view.showErrorMessage();
        }else {
            view.displayTable(table.getImage());
        }

    }
}
