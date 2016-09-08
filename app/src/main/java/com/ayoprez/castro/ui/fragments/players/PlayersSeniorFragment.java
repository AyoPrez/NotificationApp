package com.ayoprez.castro.ui.fragments.players;

/**
 * Created by ayo on 24.08.16.
 */
public class PlayersSeniorFragment extends PlayersFragment {
    private static final String TAG = PlayersSeniorFragment.class.getSimpleName();

    private static PlayersSeniorFragment instance;

    public static PlayersSeniorFragment getInstance(){
        if(instance == null){
            instance = new PlayersSeniorFragment();
        }

        return instance;
    }

    public PlayersSeniorFragment(){
        playersPresenter.setTag("Senior");
        playersPresenter.initView();
    }

}
