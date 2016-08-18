package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.Arena;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.models.TableItem;

import java.util.ArrayList;

/**
 * Created by ayo on 16.08.16.
 */
public interface GamesRepository {
    CalendarItem getCalendar();
    TableItem getTable();

    void saveCalendar(CalendarItem calendar);
    void saveTable(TableItem table);
    void closeRealm();
}
