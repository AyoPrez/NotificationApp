package com.ayoprez.castroapp.presenter.events;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.ui.fragments.events.EventView;
import com.ayoprez.castroapp.ui.MainActivity;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterImpl implements EventPresenter {

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
            view.showEmptyListMessage();
        }else{
            //Change this
//            view.changeFragment();
        }
    }
}
