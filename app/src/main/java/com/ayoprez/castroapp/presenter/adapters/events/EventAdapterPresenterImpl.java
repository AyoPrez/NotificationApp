package com.ayoprez.castroapp.presenter.adapters.events;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 10.07.16.
 */
public class EventAdapterPresenterImpl implements EventAdapterPresenter{

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
            view.showError();
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
            view.showError();
        }
    }

    @Override
    public int getEventsCountSize() {
        return repository.getAllEvents().size();
    }

}
