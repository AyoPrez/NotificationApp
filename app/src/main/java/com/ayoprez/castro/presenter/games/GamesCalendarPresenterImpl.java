package com.ayoprez.castro.presenter.games;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.ui.fragments.games.GamesCalendarView;

/**
 * Created by ayo on 24.07.16.
 */
public class GamesCalendarPresenterImpl extends ErrorManager implements GamesCalendarPresenter {
    private static final String TAG = GamesCalendarPresenterImpl.class.getSimpleName();

    protected GamesRepository repository;
    protected CalendarItem calendar;

    public GamesCalendarPresenterImpl(GamesRepository gamesRepository){
        this.repository = gamesRepository;
    }

    @Override
    public void setView(GamesCalendarView view) {
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(repository == null){
            showError(view, ERROR_EMPTY_GAMES_CALENDAR);
        } else {

            calendar = repository.getCalendar();

            if (calendar == null || calendar.getMeta().getCalendar() == null || calendar.getMeta().getCalendar().equals("")) {
                showError(view, ERROR_NO_DATA_GAMES_CALENDAR);
            } else {
                view.displayCalendar(calendar.getMeta().getCalendar());
            }
        }
    }
}
