package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.EventItem;

import java.util.ArrayList;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventsRepository {
    EventItem getEvent(int id);
    ArrayList<EventItem> getAllEvents();

    void saveEvents(ArrayList<EventItem> events);
    void deleteAllEvents();
    void closeRealm();
}
