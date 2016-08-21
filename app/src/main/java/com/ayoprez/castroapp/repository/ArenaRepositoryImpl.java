package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.Arena;
import com.ayoprez.castroapp.models.ArenaMeta;
import com.ayoprez.castroapp.models.ArenaMetaCoordinates;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.models.PlayerItemMeta;

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
        return arenaRealm.where(Arena.class).findFirst();
    }

    @Override
    public void saveArena(final Arena arena) {
        Arena arenaItem;
        ArenaMeta arenaItemMeta;
        ArenaMetaCoordinates arenaMetaCoordinates;

        Realm arenaRealm = Realm.getDefaultInstance();

        arenaRealm.beginTransaction();

        arenaItem = new Arena();
        arenaItem.setId(arena.getId());
        arenaItem.setTitle(arena.getTitle());

        arenaItemMeta = new ArenaMeta();
        arenaItemMeta.setPhoto(arena.getMeta().getPhoto());
        arenaItemMeta.setDescription(arena.getMeta().getDescription());
        arenaItem.setMeta(arenaItemMeta);

        arenaMetaCoordinates = new ArenaMetaCoordinates();
        arenaMetaCoordinates.setAddress(arena.getMeta().getCoordinates().getAddress());
        arenaMetaCoordinates.setLat(arena.getMeta().getCoordinates().getLat());
        arenaMetaCoordinates.setLng(arena.getMeta().getCoordinates().getLng());
        arenaItemMeta.setCoordinates(arenaMetaCoordinates);


        ArenaMetaCoordinates metaCoordinates = arenaRealm.copyToRealm(arenaMetaCoordinates);
        ArenaMeta arenaMeta = arenaRealm.copyToRealm(arenaItemMeta);
        Arena item = arenaRealm.copyToRealm(arenaItem);

        arenaRealm.commitTransaction();
    }

    @Override
    public void deleteArena() {
        arenaRealm.executeTransaction(new Realm.Transaction() {
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
    }

    @Override
    public void closeRealm() {
        arenaRealm.close();
    }
}
