package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.models.CalendarItemMeta;
import com.ayoprez.castroapp.models.TableItem;
import com.ayoprez.castroapp.models.TableItemMeta;

import io.realm.Realm;
import io.realm.RealmResults;

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
        CalendarItem calendarItem;
        CalendarItemMeta calendarItemMeta;

        Realm gamesRealm = Realm.getDefaultInstance();

        gamesRealm.beginTransaction();

        calendarItem = new CalendarItem();
        calendarItem.setId(calendar.getId());
        calendarItem.setTitle(calendar.getTitle());

        calendarItemMeta = new CalendarItemMeta();
        calendarItemMeta.setCalendar(calendar.getMeta().getCalendar());
        calendarItem.setMeta(calendarItemMeta);

        CalendarItemMeta itemMeta = gamesRealm.copyToRealm(calendarItemMeta);
        CalendarItem itemTable = gamesRealm.copyToRealm(calendarItem);

        gamesRealm.commitTransaction();

    }

    @Override
    public void saveTable(final TableItem table) {
        TableItem tableItem;
        TableItemMeta tableItemMeta;

        Realm gamesRealm = Realm.getDefaultInstance();

        gamesRealm.beginTransaction();

        tableItem = new TableItem();
        tableItem.setId(table.getId());
        tableItem.setTitle(table.getTitle());

        tableItemMeta = new TableItemMeta();
        tableItemMeta.setTabla(table.getMeta().getTabla());
        tableItem.setMeta(tableItemMeta);

        TableItemMeta itemMeta = gamesRealm.copyToRealm(tableItemMeta);
        TableItem itemTable = gamesRealm.copyToRealm(tableItem);

        gamesRealm.commitTransaction();
    }

    @Override
    public void deleteCalendar() {
        gamesRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<CalendarItem> result = realm.where(CalendarItem.class).findAll();
                RealmResults<CalendarItemMeta> metaResult = realm.where(CalendarItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void deleteTable() {
        gamesRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<TableItem> result = realm.where(TableItem.class).findAll();
                RealmResults<TableItemMeta> metaResult = realm.where(TableItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });
    }

    @Override
    public void closeRealm() {
        gamesRealm.close();
    }

}
