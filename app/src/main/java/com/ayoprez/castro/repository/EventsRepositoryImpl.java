package com.ayoprez.castro.repository;

import com.ayoprez.castro.R;
import com.ayoprez.castro.common.TimeUtils;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import io.realm.Realm;
import io.realm.RealmConfiguration;
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
        EventItem eventItem;
        EventItemMeta eventItemMeta;

        TimeUtils timeUtils = new TimeUtils();

        Realm eventRealm = Realm.getDefaultInstance();

        for(EventItem event : events) {

            if(timeUtils.isFutureDate(event.getMeta().getDate(), event.getMeta().getTime())) {

                eventRealm.beginTransaction();

                eventItem = new EventItem();
                eventItem.setId(event.getId() + getLastId());
                eventItem.setTitle(event.getTitle());

                eventItemMeta = new EventItemMeta();
                eventItemMeta.setImage(event.getMeta().getImage());
                eventItemMeta.setDescription(event.getMeta().getDescription());
                eventItemMeta.setDate(event.getMeta().getDate());
                eventItemMeta.setPrice(event.getMeta().getPrice());
                eventItemMeta.setSubtitle(event.getMeta().getSubtitle());
                eventItemMeta.setTime(event.getMeta().getTime());
                eventItem.setMeta(eventItemMeta);

                EventItemMeta itemMeta = eventRealm.copyToRealm(eventItemMeta);
                EventItem itemTable = eventRealm.copyToRealm(eventItem);

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
