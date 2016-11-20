package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.CalendarItem;
import com.ayoprez.castro.model.models.CalendarItemMeta;
import com.ayoprez.castro.model.models.TableItem;
import com.ayoprez.castro.model.models.TableItemMeta;

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
        return Realm.getDefaultInstance().where(CalendarItem.class).findFirst();
    }

    @Override
    public TableItem getTable() {
        return Realm.getDefaultInstance().where(TableItem.class).findFirst();
    }

    @Override
    public void saveCalendar(final CalendarItem calendar) {
        Realm gamesRealm = Realm.getDefaultInstance();

        gamesRealm.beginTransaction();

        CalendarItemMeta itemMeta = gamesRealm.copyToRealm(calendar.getMeta());
        CalendarItem itemTable = gamesRealm.copyToRealm(calendar);

        gamesRealm.commitTransaction();

        gamesRealm.close();
    }

    @Override
    public void saveTable(final TableItem table) {
        Realm gamesRealm = Realm.getDefaultInstance();

        gamesRealm.beginTransaction();

        TableItemMeta itemMeta = gamesRealm.copyToRealm(table.getMeta());
        TableItem itemTable = gamesRealm.copyToRealm(table);

        gamesRealm.commitTransaction();

        gamesRealm.close();
    }

    @Override
    public void deleteCalendar() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<CalendarItem> result = realm.where(CalendarItem.class).findAll();
                RealmResults<CalendarItemMeta> metaResult = realm.where(CalendarItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void deleteTable() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<TableItem> result = realm.where(TableItem.class).findAll();
                RealmResults<TableItemMeta> metaResult = realm.where(TableItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void closeRealm() {
        gamesRealm.close();
    }

}
