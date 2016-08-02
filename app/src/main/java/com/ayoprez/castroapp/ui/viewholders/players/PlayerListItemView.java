package com.ayoprez.castroapp.ui.viewholders.players;

/**
 * Created by ayo on 10.07.16.
 */
public interface PlayerListItemView {
    int getItemPosition();

    void displayItemTitle(String title);
//    void displayItemSubtitle(String subtitle);
    void displayItemImage(String image);

    void showError(); //TODO how?
}
