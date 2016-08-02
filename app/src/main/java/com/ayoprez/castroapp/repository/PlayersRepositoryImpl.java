package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.PlayerItem;

import java.util.ArrayList;

import io.realm.Realm;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersRepositoryImpl implements PlayersRepository {

    private Realm playerRealm;

    public PlayersRepositoryImpl(){
        playerRealm = Realm.getDefaultInstance();
    }

    @Override
    public PlayerItem getPlayer(int id) {
        return playerRealm.where(PlayerItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<PlayerItem> getAllPlayers() {
        return new ArrayList<>(playerRealm.where(PlayerItem.class).findAll());
    }

    @Override
    public void savePlayers(final ArrayList<PlayerItem> players) {
        playerRealm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                PlayerItem playerItemTable = playerRealm.createObject(PlayerItem.class);

                for(PlayerItem player : players){
                    playerItemTable.setObject(player);
                }
            }
        });
    }

    @Override
    public void closeRealm() {
        playerRealm.close();
    }
}
