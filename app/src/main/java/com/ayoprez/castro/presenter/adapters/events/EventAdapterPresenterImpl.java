package com.ayoprez.castro.presenter.adapters.events;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.CommonListItemView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public class EventAdapterPresenterImpl extends ErrorManager implements EventAdapterPresenter{

    protected EventListItemView view;
    protected EventItem eventItem;
    protected EventsRepository repository;

    public EventAdapterPresenterImpl(EventsRepository eventsRepository){
        this.repository = eventsRepository;
    }

    @Override
    public void setView(EventListItemView view) {
        this.view = view;
        loadEventData();
    }

    @Override
    public void loadEventData() {
        if(view == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = view.getEventPosition();
        eventItem = repository.getEvent(itemPosition);

        if(eventItem == null){
            showError(view, ERROR_EMPTY_EVENTS, itemPosition);
        }else{
            applyDisplayEvents(eventItem);
        }
    }

    private void applyDisplayEvents(EventItem eventItem){
        if(!eventItem.getMeta().getImage().isEmpty() && !eventItem.getTitle().isEmpty() && !eventItem.getMeta().getSubtitle().isEmpty()){
            view.displayEventImage(eventItem.getMeta().getImage());
            view.displayEventTitle(eventItem.getTitle());
            view.displayEventSubtitle(eventItem.getMeta().getSubtitle());
        }else{
            showError(view, ERROR_NO_DATA_EVENT, view.getEventPosition());
        }
    }

    @Override
    public int getEventsCountSize() {
        return repository.getAllEvents().size();
    }

}
