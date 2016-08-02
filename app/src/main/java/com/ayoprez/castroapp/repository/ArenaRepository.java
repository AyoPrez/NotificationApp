package com.ayoprez.castroapp.repository;

import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.models.Arena;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaRepository {
    Arena getArena();

    void saveArena(Arena arena);
    void closeRealm();
}
