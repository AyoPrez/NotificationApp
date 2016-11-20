package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.common.TimeUtils;
import com.ayoprez.castro.model.models.EventItem;
import com.ayoprez.castro.model.models.EventItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 26.06.16.
 */
public class EventsRepositoryImpl implements EventsRepository {

    private Realm eventRealm;
    private int lastId;

    public EventsRepositoryImpl(){
        eventRealm = Realm.getDefaultInstance();
    }

    @Override
    public EventItem getEvent(int id) {
        return eventRealm.where(EventItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<EventItem> getAllEvents() {
        return new ArrayList<>(Realm.getDefaultInstance().where(EventItem.class).findAll());
    }

    @Override
    public int getEventIdByPosition(int position) {
        return getAllEvents().get(position).getId();
    }

    @Override
    public EventItem getEventByPosition(int position) {
        return getAllEvents().get(position);
    }

    @Override
    public void saveEvents(final ArrayList<EventItem> events) {
        TimeUtils timeUtils = new TimeUtils();

         Realm eventRealm = Realm.getDefaultInstance();

        for(EventItem event : events) {

            if(timeUtils.isFutureDate(event.getMeta().getDate(), event.getMeta().getTime())) {

                eventRealm.beginTransaction();

                event.setId(event.getId() + getLastId());

                EventItemMeta itemMeta = eventRealm.copyToRealm(event.getMeta());
                EventItem itemTable = eventRealm.copyToRealm(event);

                eventRealm.commitTransaction();
            }
        }

        eventRealm.close();
    }

    @Override
    public void deleteAllEvents() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<EventItem> result = realm.where(EventItem.class).findAll();
                RealmResults<EventItemMeta> metaResult = realm.where(EventItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    private int getLastId(){
        return lastId++;
    }

    @Override
    public void closeRealm() {
        eventRealm.close();
    }
}
