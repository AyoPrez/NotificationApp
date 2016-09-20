package com.ayoprez.castro.ui.fragments.events;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.models.EventItem;

import java.util.HashMap;
import java.util.StringTokenizer;

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

    void shareContent(HashMap<String, String> eventItemData);
    void notifyAlarmEvent(long time, String eventTitle);
}
