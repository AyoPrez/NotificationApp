package com.ayoprez.castro.presenter.games;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.model.models.TableItem;
import com.ayoprez.castro.model.repository.GamesRepository;
import com.ayoprez.castro.ui.fragments.games.GamesTableView;

/**
 * Created by ayo on 17.08.16.
 */
public class GamesTablePresenterImpl extends ErrorManager implements GamesTablePresenter {
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
            showError(view, ERROR_EMPTY_GAMES_TABLE);
        }else {
            table = repository.getTable();

            if (table == null || table.getMeta().getTabla() == null || table.getMeta().getTabla().equals("")) {
                showError(view, ERROR_NO_DATA_GAMES_TABLE);
            } else {
                view.displayTable(table.getMeta().getTabla());
            }
        }
    }
}
