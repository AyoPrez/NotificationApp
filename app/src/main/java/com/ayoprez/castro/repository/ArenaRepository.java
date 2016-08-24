package com.ayoprez.castro.repository;

import com.ayoprez.castro.models.Arena;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaRepository {
    Arena getArena();

    void saveArena(Arena arena);
    void deleteArena();
    void closeRealm();
}
