package com.ayoprez.castroapp.presenter;

import com.ayoprez.castroapp.ui.fragments.games.GamesView;

/**
 * Created by ayo on 24.07.16.
 */
public class GamesPresenterImpl implements GamesPresenter {
    private static final String TAG = GamesPresenterImpl.class.getSimpleName();

    private GamesView view;

    @Override
    public void setView(GamesView view) {
        this.view = view;
    }
}
