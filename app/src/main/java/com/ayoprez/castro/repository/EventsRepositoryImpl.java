package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 26.06.16.
 */
public class EventsRepositoryImpl implements EventsRepository{

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
        return new ArrayList<>(eventRealm.where(EventItem.class).findAll());
    }

    @Override
    public void saveEvents(final ArrayList<EventItem> events) {
        EventItem eventItem;
        EventItemMeta eventItemMeta;

        Realm eventRealm = Realm.getDefaultInstance();

        for(EventItem player : events) {
            eventRealm.beginTransaction();

            eventItem = new EventItem();
            eventItem.setId(player.getId()+getLastId());
            eventItem.setTitle(player.getTitle());

            eventItemMeta = new EventItemMeta();
            eventItemMeta.setImage(player.getMeta().getImage());
            eventItemMeta.setDescription(player.getMeta().getDescription());
            eventItemMeta.setDate(player.getMeta().getDate());
            eventItemMeta.setPrice(player.getMeta().getPrice());
            eventItemMeta.setSubtitle(player.getMeta().getSubtitle());
            eventItemMeta.setTime(player.getMeta().getTime());
            eventItem.setMeta(eventItemMeta);

            EventItemMeta itemMeta = eventRealm.copyToRealm(eventItemMeta);
            EventItem itemTable = eventRealm.copyToRealm(eventItem);

            eventRealm.commitTransaction();
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
