package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.NotificationEvents;

import java.util.ArrayList;

/**
 * Created by ayo on 02.10.16.
 */

public interface NotificationEventsRepository {

    NotificationEvents getNotificationEvent(short id);
    NotificationEvents getNotificationEventByEventId(short id);
    ArrayList<NotificationEvents> getAllNotificationEvents();
    int getNotificationEventIdByPosition(int position);
    NotificationEvents getNotificationEventByPosition(int position);

    void saveNotificationEvents(EventItem events);
    boolean isEventScheduled(short id);
    void deleteAllNotificationEvents();
    void deleteNotificationEventById(short id);
    void deleteNotificationEventByEventId(short id);
    void closeRealm();
}
