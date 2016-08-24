package com.ayoprez.castro.ui.fragments.arena;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 03.07.16.
 */
public interface ArenaView extends CommonActivityView{

    void displayName(String name);
    void displayAddress(String address);
    void displayDescription(String description);
    void displayImage(String image);
    void clickMapButton(String address);
    void displayMap(String address);
}
