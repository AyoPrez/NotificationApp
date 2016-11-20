package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.EventItem;

import java.util.ArrayList;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventsRepository {
    EventItem getEvent(int id);
    ArrayList<EventItem> getAllEvents();
    int getEventIdByPosition(int position);
    EventItem getEventByPosition(int position);

    void saveEvents(ArrayList<EventItem> events);
    void deleteAllEvents();
    void closeRealm();
}
