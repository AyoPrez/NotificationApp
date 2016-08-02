package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.models.PlayerItem;

import java.util.ArrayList;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventsRepository {
    EventItem getEvent(int id);
    ArrayList<EventItem> getAllEvents();

    void saveEvents(ArrayList<EventItem> events);
    void closeRealm();
}
