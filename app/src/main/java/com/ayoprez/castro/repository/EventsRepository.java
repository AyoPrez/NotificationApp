package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.EventItem;

import java.util.ArrayList;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventsRepository {
    EventItem getEvent(short id);
    ArrayList<EventItem> getAllEvents();
    int getEventIdByPosition(int position);
    EventItem getEventByPosition(int position);

    void saveEvents(ArrayList<EventItem> events);
    void deleteAllEvents();
    void closeRealm();
}
