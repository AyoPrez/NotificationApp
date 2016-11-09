package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 20.08.16.
 */
public interface GamesRestfulService {
    void getRestfulCalendarGames(CommonActivityView view);
    void getRestfulTableGames(CommonActivityView view);
    void deleteCalendarGamesData();
    void deleteTableGamesData();
}
