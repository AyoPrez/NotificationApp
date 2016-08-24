package com.ayoprez.castro.presenter.events;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.ui.fragments.events.EventView;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterImpl extends ErrorManager implements EventPresenter {

    private EventView view;

    @Override
    public void setView(EventView view) {
        this.view = view;

        if(view == null){
            throw new ViewNotFoundException();
        }else{
            this.view.initRecyclerView();
        }
    }

    @Override
    public void openDetailedView(EventItem item) {
        if(item == null || item.getTitle() == null || item.getTitle().trim().equals("")) {
            showError(view, ERROR_EMPTY_EVENTS);
        }else{
            //Change this
//            view.changeFragment();
        }
    }
}
