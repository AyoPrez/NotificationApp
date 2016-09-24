package com.ayoprez.castro.presenter.adapters.events;

import android.view.View;

import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.ui.fragments.events.EventListView;
import com.ayoprez.castro.ui.fragments.events.EventView;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public interface EventAdapterPresenter {
    void setListItemView(EventListItemView view);
    void setEventView(EventView eventView, short eventId);

    void loadEventData();

    int getEventsCountSize(EventListView eventListView);

    void shareEventContent(short id);
    void notifyEvent(short id);

    void openDetailedView(EventListView eventListView, int position);
}
