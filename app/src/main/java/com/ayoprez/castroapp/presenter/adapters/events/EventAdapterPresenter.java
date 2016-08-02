package com.ayoprez.castroapp.presenter.adapters.events;

import com.ayoprez.castroapp.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public interface EventAdapterPresenter {


    void setView(EventListItemView view);
    void loadEventData();

    int getEventsCountSize();
}
