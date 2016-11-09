package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.models.TableItem;
import com.ayoprez.castro.repository.GamesRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 20.08.16.
 */
public class GamesRestfulServiceImpl extends ErrorManager implements GamesRestfulService {
    private static final String TAG = GamesRestfulServiceImpl.class.getSimpleName();

    private GamesRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public GamesRestfulServiceImpl(GamesRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulCalendarGames(final CommonActivityView view) {
        subscription = service.getCalendarFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<CalendarItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCalendarGamesData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_GAMES);
                    }

                    @Override
                    public void onNext(ArrayList<CalendarItem> calendarItems) {
                        repository.saveCalendar(calendarItems.get(0));
                    }
                });
    }

    @Override
    public void getRestfulTableGames(final CommonActivityView view) {
        subscription = service.getTableFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<TableItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteTableGamesData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_GAMES);
                    }

                    @Override
                    public void onNext(ArrayList<TableItem> tableItems) {
                        repository.saveTable(tableItems.get(0));
                    }
                });
    }

    @Override
    public void deleteCalendarGamesData() {
        if(repository.getCalendar() != null){
            repository.deleteCalendar();
        }
    }

    @Override
    public void deleteTableGamesData() {
        if(repository.getTable() != null){
            repository.deleteTable();
        }
    }
}
