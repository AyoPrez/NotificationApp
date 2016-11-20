package com.ayoprez.castro.ui.fragments.players;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.model.models.PlayerItem;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayersView extends CommonActivityView {
    void initRecyclerView(ArrayList<PlayerItem> content);
}
