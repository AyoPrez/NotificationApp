package com.ayoprez.castroapp.presenter.games;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.repository.GamesRepository;
import com.ayoprez.castroapp.ui.fragments.games.GamesCalendarView;

/**
 * Created by ayo on 24.07.16.
 */
public class GamesCalendarPresenterImpl implements GamesCalendarPresenter {
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
            view.showErrorMessage();
        }

        calendar = repository.getCalendar();

        if(calendar == null || calendar.getImage() == null || calendar.getImage().equals("")){
            view.showErrorMessage();
        }else {
            view.displayCalendar(calendar.getImage());
        }
    }
}
