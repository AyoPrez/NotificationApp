package com.ayoprez.castro.ui.fragments.players;

/**
 * Created by ayo on 24.08.16.
 */
public class PlayersJuniorFragment extends PlayersFragment {
    private static final String TAG = PlayersSeniorFragment.class.getSimpleName();

    private static PlayersJuniorFragment instance;

    public static PlayersJuniorFragment getInstance(){
        if(instance == null){
            instance = new PlayersJuniorFragment();
        }

        return instance;
    }

    public PlayersJuniorFragment(){
        playersPresenter.setTag("Junior");
        playersPresenter.initView();
    }
}
