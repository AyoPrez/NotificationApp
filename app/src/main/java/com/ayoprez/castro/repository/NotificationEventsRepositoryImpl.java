package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.NotificationEvents;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 02.10.16.
 */

public class NotificationEventsRepositoryImpl implements NotificationEventsRepository {

    private Realm eventRealm;

    public NotificationEventsRepositoryImpl(){
        eventRealm = Realm.getDefaultInstance();
    }

    @Override
    public NotificationEvents getNotificationEvent(int id) {
        return eventRealm.where(NotificationEvents.class).equalTo("id", id).findFirst();
    }

    @Override
    public NotificationEvents getNotificationEventByEventId(int id) {
        return eventRealm.where(NotificationEvents.class).equalTo("eventId", id).findFirst();
    }

    @Override
    public ArrayList<NotificationEvents> getAllNotificationEvents() {
        return new ArrayList<>(Realm.getDefaultInstance().where(NotificationEvents.class).findAll());
    }

    @Override
    public int getNotificationEventIdByPosition(int position) {
        return getAllNotificationEvents().get(position).getId();
    }

    @Override
    public NotificationEvents getNotificationEventByPosition(int position) {
        return getAllNotificationEvents().get(position);
    }

    @Override
    public void saveNotificationEvents(EventItem events) {
        NotificationEvents notificationEvent;

        Realm eventRealm = Realm.getDefaultInstance();

        eventRealm.beginTransaction();

        notificationEvent = new NotificationEvents();
        notificationEvent.setId(getLastId());
        notificationEvent.setEventId(events.getId());
        notificationEvent.setEventTitle(events.getTitle());

        NotificationEvents itemTable = eventRealm.copyToRealm(notificationEvent);

        eventRealm.commitTransaction();

        eventRealm.close();
    }

    @Override
    public boolean isEventScheduled(int id) {
        return getNotificationEventByEventId(id) != null;
    }

    private int getLastId(){
        ArrayList<?> list = getAllNotificationEvents();

        if(list.size() != 0){
            return getAllNotificationEvents().get(list.size() - 1).getId() + 1;
        }else {
            return 0;
        }


    }

    @Override
    public void deleteAllNotificationEvents() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<NotificationEvents> result = realm.where(NotificationEvents.class).findAll();
                result.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void deleteNotificationEventById(final int id) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NotificationEvents result = realm.where(NotificationEvents.class).equalTo("id", id).findFirst();
                result.deleteFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void deleteNotificationEventByEventId(final short id) {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                NotificationEvents result = realm.where(NotificationEvents.class).equalTo("eventId", id).findFirst();
                result.deleteFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void closeRealm() {
        eventRealm.close();
    }
}
