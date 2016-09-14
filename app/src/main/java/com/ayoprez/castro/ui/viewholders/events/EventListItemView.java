package com.ayoprez.castro.ui.viewholders.events;

import com.ayoprez.castro.common.CommonListItemView;
import com.ayoprez.castro.models.EventItem;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventListItemView extends CommonListItemView {
    int getEventPosition();

    void displayEventTitle(String title);
    void displayEventSubtitle(String subtitle);
    void displayEventImage(String image);
}
