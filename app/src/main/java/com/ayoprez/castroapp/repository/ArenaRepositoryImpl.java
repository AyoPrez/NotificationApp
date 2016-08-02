package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.Arena;

import io.realm.Realm;

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
        return arenaRealm.where(Arena.class).findFirst();
    }

    @Override
    public void saveArena(final Arena arena) {
        arenaRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                Arena arenaTable = arenaRealm.createObject(Arena.class);
                arenaTable.setObject(arena);
            }
        });
    }

    @Override
    public void closeRealm() {
        arenaRealm.close();
    }
}
