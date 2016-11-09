package com.ayoprez.castro.restful;


import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.common.TimeUtils;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.repository.EventsRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 20.08.16.
 */
public class EventsRestfulServiceImpl extends ErrorManager implements EventsRestfulService {
    private static final String TAG = EventsRestfulServiceImpl.class.getSimpleName();

    private EventsRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public EventsRestfulServiceImpl(EventsRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulEvents(final CommonActivityView view) {

        subscription = service.getEventsFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<EventItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompleteEventsData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_EVENTS);
                    }

                    @Override
                    public void onNext(ArrayList<EventItem> events) {
                        repository.saveEvents(events);
                    }
                });
    }

    @Override
    public void deleteCompleteEventsData() {
        if(repository.getAllEvents().size() > 0){
            repository.deleteAllEvents();
        }
    }

    private ArrayList<EventItem> getSortArrayListByDate(ArrayList<EventItem> eventList){

        final TimeUtils timeUtils = new TimeUtils();

        Collections.sort(eventList, new Comparator<EventItem>() {
            public int compare(EventItem m1, EventItem m2) {

                Date date1 = new Date(timeUtils.getDateInMilliseconds(m1.getMeta().getDate(), m1.getMeta().getTime()));
                Date date2 = new Date(timeUtils.getDateInMilliseconds(m2.getMeta().getDate(), m2.getMeta().getTime()));

                return date1.compareTo(date2);
            }
        });

        return eventList;
    }
}
