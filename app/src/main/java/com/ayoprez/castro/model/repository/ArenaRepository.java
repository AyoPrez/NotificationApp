package com.ayoprez.castro.model.repository;

import com.ayoprez.castro.model.models.Arena;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaRepository {
    Arena getArena();

    void saveArena(Arena arena);
    void deleteArena();
    void closeRealm();
}
