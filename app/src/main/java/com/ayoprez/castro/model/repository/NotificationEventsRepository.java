package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.EventItem;
import com.ayoprez.castro.model.models.NotificationEvents;

import java.util.ArrayList;

/**
 * Created by ayo on 02.10.16.
 */

public interface NotificationEventsRepository {

    NotificationEvents getNotificationEvent(int id);
    NotificationEvents getNotificationEventByEventId(int id);
    ArrayList<NotificationEvents> getAllNotificationEvents();
    int getNotificationEventIdByPosition(int position);
    NotificationEvents getNotificationEventByPosition(int position);

    void saveNotificationEvents(EventItem events);
    boolean isEventScheduled(int id);
    void deleteAllNotificationEvents();
    void deleteNotificationEventById(int id);
    void deleteNotificationEventByEventId(short id);
    void closeRealm();
}
