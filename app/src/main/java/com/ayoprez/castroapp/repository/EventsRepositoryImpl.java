package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.EventItem;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ayo on 26.06.16.
 */
public class EventsRepositoryImpl implements EventsRepository{

    Realm eventRealm;

    public EventsRepositoryImpl(){
        eventRealm = Realm.getDefaultInstance();
    }

    @Override
    public EventItem getEvent(int id) {
        return eventRealm.where(EventItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<EventItem> getAllEvents() {
        return new ArrayList<>(eventRealm.where(EventItem.class).findAll());
    }

    @Override
    public void saveEvents(final ArrayList<EventItem> events) {
        eventRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                EventItem eventItemTable = eventRealm.createObject(EventItem.class);

                for(EventItem event : events){
                    eventItemTable.setObject(event);
                }
            }
        });
    }

    @Override
    public void closeRealm() {
        eventRealm.close();
    }
}
