package com.ayoprez.castro.presenter.adapters.events;

import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public interface EventAdapterPresenter {


    void setView(EventListItemView view);
    void loadEventData();

    int getEventsCountSize();
}
