package com.ayoprez.castro.ui.fragments.events;

import com.ayoprez.castro.common.CommonActivityView;

import java.util.HashMap;

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
    void displayNotificationButtonState(boolean state);
    void buttonNotify();
    void buttonShare();
    void confirmNotification();

    void shareContent(HashMap<String, String> eventItemData);
    void notifyAlarmEvent(String date, String time, String eventTitle);
}
