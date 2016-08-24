package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.models.TableItem;

/**
 * Created by ayo on 16.08.16.
 */
public interface GamesRepository {
    CalendarItem getCalendar();
    TableItem getTable();

    void saveCalendar(CalendarItem calendar);
    void saveTable(TableItem table);
    void deleteCalendar();
    void deleteTable();
    void closeRealm();
}
