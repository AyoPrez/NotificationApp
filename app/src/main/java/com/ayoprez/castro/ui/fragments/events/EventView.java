package com.ayoprez.castro.ui.fragments.events;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 10.09.16.
 */
public interface EventView extends CommonActivityView {

    void displayTitle(String title);
    void displaySubtitle(String subtitle);
    void displayTime(String time);
    void displayDate(String date);
    void displayDescription(String description);
    void displayImage(String image);
    void displayPrice(String price);
    void buttonNotify();
    void buttonShare();
}
