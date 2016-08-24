package com.ayoprez.castro.presenter.events;

import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.ui.fragments.events.EventView;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventPresenter {

    void setView(EventView view);
    void openDetailedView(EventItem item);
}
