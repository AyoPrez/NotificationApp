package com.ayoprez.castroapp.presenter.events;

import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.ui.fragments.events.EventView;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventPresenter {

    void setView(EventView view);
    void openDetailedView(EventItem item);
}
