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
    private short lastId;

    public NotificationEventsRepositoryImpl(){
        eventRealm = Realm.getDefaultInstance();
    }

    @Override
    public NotificationEvents getNotificationEvent(short id) {
        return eventRealm.where(NotificationEvents.class).equalTo("id", id).findFirst();
    }

    @Override
    public NotificationEvents getNotificationEventByEventId(short id) {
        return eventRealm.where(NotificationEvents.class).equalTo("eventId", id).findFirst();
    }

    @Override
    public ArrayList<NotificationEvents> getAllNotificationEvents() {
        return new ArrayList<>(eventRealm.where(NotificationEvents.class).findAll());
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
        notificationEvent.setId((short) (notificationEvent.getId() + getLastId()));
        notificationEvent.setEventId(events.getId());
        notificationEvent.setEventTitle(events.getTitle());

        NotificationEvents itemTable = eventRealm.copyToRealm(notificationEvent);

        eventRealm.commitTransaction();

        eventRealm.close();
    }

    @Override
    public boolean isEventScheduled(short id) {
        return getNotificationEventByEventId(id) != null;
    }

    private short getLastId(){
        return lastId++;
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
    public void deleteNotificationEventById(final short id) {
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
