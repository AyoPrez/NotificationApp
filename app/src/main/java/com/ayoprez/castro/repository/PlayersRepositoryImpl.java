package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.models.PlayerItemMeta;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersRepositoryImpl implements PlayersRepository {

    private Realm playerRealm;
    private int lastId;

    public PlayersRepositoryImpl() {
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
        PlayerItem playerItem;
        PlayerItemMeta itemMeta;

        Realm playerRealm = Realm.getDefaultInstance();

        for(PlayerItem player : players) {
            playerRealm.beginTransaction();

            playerItem = new PlayerItem();
            playerItem.setId(player.getId()+getLastId());
            playerItem.setTitle(player.getTitle());

            itemMeta = new PlayerItemMeta();
            itemMeta.setPhoto(player.getMeta().getPhoto());
            itemMeta.setCategory(player.getMeta().getCategory());
            playerItem.setMeta(itemMeta);

            PlayerItemMeta playerItemMeta = playerRealm.copyToRealm(itemMeta);
            PlayerItem playerItemTable = playerRealm.copyToRealm(playerItem);

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
        return lastId++;
    }

    @Override
    public void closeRealm() {
        playerRealm.close();
    }
}
