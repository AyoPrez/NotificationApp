package com.ayoprez.castro.ui.fragments.players;

/**
 * Created by ayo on 24.08.16.
 */
public class PlayersCadetFragment extends PlayersFragment {
    private static final String TAG = PlayersSeniorFragment.class.getSimpleName();

    private static PlayersCadetFragment instance;

    public static PlayersCadetFragment getInstance(){
        if(instance == null){
            instance = new PlayersCadetFragment();
        }

        return instance;
    }

    public PlayersCadetFragment(){
        playersPresenter.setTag("Cadet");
        playersPresenter.initView();
    }
}
