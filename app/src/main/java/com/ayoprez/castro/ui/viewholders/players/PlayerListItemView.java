package com.ayoprez.castro.ui.viewholders.players;

import com.ayoprez.castro.common.CommonListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayerListItemView extends CommonListItemView {
    int getItemPosition();

    void displayItemTitle(String title);
//    void displayItemSubtitle(String subtitle);
    void displayItemImage(String image);

}
