package com.ayoprez.castroapp.ui.fragments.arena;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaView {

    void displayName(String name);
    void displayAddress(String address);
    void displayDescription(String description);
    void displayImage(String image);
    void clickMapButton(String address);
    void displayMap(String address);

    void showErrorMessage();

}
