package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.Arena;
import com.ayoprez.castro.model.models.ArenaMeta;
import com.ayoprez.castro.model.models.ArenaMetaCoordinates;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 03.07.16.
 */
public class ArenaRepositoryImpl implements ArenaRepository {

    private Realm arenaRealm;

    public ArenaRepositoryImpl(){
        arenaRealm = Realm.getDefaultInstance();
    }

    @Override
    public Arena getArena() {
        return Realm.getDefaultInstance().where(Arena.class).findFirst();
    }

    @Override
    public void saveArena(final Arena arena) {
        Realm arenaRealm = Realm.getDefaultInstance();

        arenaRealm.beginTransaction();

        ArenaMetaCoordinates metaCoordinates = arenaRealm.copyToRealm(arena.getMeta().getCoordinates());
        ArenaMeta arenaMeta = arenaRealm.copyToRealm(arena.getMeta());
        Arena item = arenaRealm.copyToRealm(arena);

        arenaRealm.commitTransaction();

        arenaRealm.close();
    }

    @Override
    public void deleteArena() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<Arena> result = realm.where(Arena.class).findAll();
                RealmResults<ArenaMeta> metaResult = realm.where(ArenaMeta.class).findAll();
                RealmResults<ArenaMetaCoordinates> metaCoordinatesResult = realm.where(ArenaMetaCoordinates.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
                metaCoordinatesResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    @Override
    public void closeRealm() {
        arenaRealm.close();
    }
}
