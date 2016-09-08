package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.PlayerItem;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayersRepository {
    PlayerItem getPlayer(int id);
    ArrayList<PlayerItem> getAllPlayers();

    ArrayList<PlayerItem> getSeniorPlayers();
    ArrayList<PlayerItem> getCadetPlayers();
    ArrayList<PlayerItem> getJuniorPlayers();

    void savePlayers(ArrayList<PlayerItem> players);
    void deleteAllPlayers();
    void closeRealm();
}
