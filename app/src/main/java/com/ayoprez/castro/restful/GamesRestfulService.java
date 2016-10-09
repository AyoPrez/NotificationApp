package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 20.08.16.
 */
public interface GamesRestfulService {
    void getRestfulGames(CommonActivityView view);
    void deleteCompleteGamesData();
}
