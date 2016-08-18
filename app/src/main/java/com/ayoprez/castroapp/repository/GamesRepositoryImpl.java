package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.models.TableItem;

import io.realm.Realm;

/**
 * Created by ayo on 16.08.16.
 */
public class GamesRepositoryImpl implements GamesRepository {
    private static final String TAG = GamesRepositoryImpl.class.getSimpleName();

    Realm gamesRealm;

    public GamesRepositoryImpl(){
        gamesRealm = Realm.getDefaultInstance();
    }

    @Override
    public CalendarItem getCalendar() {
        return gamesRealm.where(CalendarItem.class).findFirst();
    }

    @Override
    public TableItem getTable() {
        return gamesRealm.where(TableItem.class).findFirst();
    }

    @Override
    public void saveCalendar(final CalendarItem calendar) {
        gamesRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                CalendarItem calendarItem = gamesRealm.createObject(CalendarItem.class);
                calendarItem.setObject(calendar);
            }
        });
    }

    @Override
    public void saveTable(final TableItem table) {
        gamesRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                TableItem tableItem = gamesRealm.createObject(TableItem.class);
                tableItem.setObject(table);
            }
        });
    }

    @Override
    public void closeRealm() {
        gamesRealm.close();
    }

}
