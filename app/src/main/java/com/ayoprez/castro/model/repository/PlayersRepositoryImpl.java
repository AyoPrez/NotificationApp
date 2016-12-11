package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.ImageItem;
import com.ayoprez.castro.model.models.PlayerItem;
import com.ayoprez.castro.model.models.PlayerItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersRepositoryImpl implements PlayersRepository {

    private Realm playerRealm;

    public PlayersRepositoryImpl() {
        playerRealm = Realm.getDefaultInstance();
    }

    @Override
    public PlayerItem getPlayer(int id) {
        return playerRealm.where(PlayerItem.class).equalTo("id", id).findFirst();
    }

    @Override
    public ArrayList<PlayerItem> getAllPlayers() {
        return new ArrayList<>(Realm.getDefaultInstance().where(PlayerItem.class).findAll());
    }

    @Override
    public ArrayList<PlayerItem> getSeniorPlayers() {
        return getCategoryPlayers("Senior");
    }

    protected ArrayList<PlayerItem> getCategoryPlayers(String category){
        ArrayList<PlayerItem> playerItems = new ArrayList<>();

        for(PlayerItem item : getAllPlayers()){
            if(item.getMeta().getCategory().equals(category) || item.getMeta().getCategory().equals(category.toLowerCase())){
                playerItems.add(item);
            }
        }

        return playerItems;
    }

    @Override
    public ArrayList<PlayerItem> getCadetPlayers() {
        return getCategoryPlayers("Cadete");
    }

    @Override
    public ArrayList<PlayerItem> getJuniorPlayers() {
        return getCategoryPlayers("Juvenil");
    }

    @Override
    public void savePlayers(final ArrayList<PlayerItem> players) {

        Realm playerRealm = Realm.getDefaultInstance();

        for(PlayerItem player : players) {
            playerRealm.beginTransaction();

            player.setId(getLastId());

            PlayerItemMeta playerItemMeta = playerRealm.copyToRealm(player.getMeta());
            PlayerItem playerItemTable = playerRealm.copyToRealm(player);

            playerRealm.commitTransaction();
        }

        playerRealm.close();
    }

    @Override
    public void deleteAllPlayers() {
        Realm.getDefaultInstance().executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                RealmResults<PlayerItem> result = realm.where(PlayerItem.class).findAll();
                RealmResults<PlayerItemMeta> metaResult = realm.where(PlayerItemMeta.class).findAll();
                result.deleteAllFromRealm();
                metaResult.deleteAllFromRealm();
            }
        });

        Realm.getDefaultInstance().close();
    }

    private int getLastId(){
        ArrayList<PlayerItem> list = getAllPlayers();

        if(list.size() != 0){
            return list.get(list.size() - 1).getId() + 1;
        }else {
            return 0;
        }
    }

    @Override
    public void closeRealm() {
        playerRealm.close();
    }
}
