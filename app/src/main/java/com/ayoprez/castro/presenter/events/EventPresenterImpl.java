package com.ayoprez.castro.presenter.events;

import android.os.Bundle;
import android.view.View;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.ui.fragments.events.EventFragment;
import com.ayoprez.castro.ui.fragments.events.EventListView;
import com.ayoprez.castro.ui.fragments.events.EventView;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterImpl extends ErrorManager implements EventPresenter, EventAdapterPresenter {

    protected static EventListView listView;
    protected EventListItemView listItemView;
    protected EventView eventView;
    protected EventItem eventItem;
    protected EventsRepository repository;

    public EventPresenterImpl(EventsRepository eventsRepository) {
        this.repository = eventsRepository;
    }

    @Override
    public void setView(EventListView view) {
        if(listView == null) {
            listView = view;
        }

        if(view == null){
            throw new ViewNotFoundException();
        }else{
            try {
                view.initRecyclerView();
            }catch(Exception e){
                showError(listView, ERROR);
            }
        }
    }

    @Override
    public void setListItemView(EventListItemView view) {
        this.listItemView = view;
        loadEventData();
    }

    @Override
    public void setEventView(EventView eventView, EventItem eventItem) {
        this.eventView = eventView;

        applyDetailsDisplayEvent(eventItem);
    }

    @Override
    public void loadEventData() {
        if(listItemView == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = listItemView.getEventPosition();
        eventItem = repository.getEvent(itemPosition);

        if(eventItem == null){
            showError(listItemView, ERROR_EMPTY_EVENTS, itemPosition);
        }else{
            applyListDisplayEvents(eventItem);
        }
    }

    private void applyListDisplayEvents(EventItem eventItem) {
        if(!eventItem.getMeta().getImage().isEmpty() && !eventItem.getTitle().isEmpty() && !eventItem.getMeta().getSubtitle().isEmpty()){
            listItemView.displayEventImage(eventItem.getMeta().getImage());
            listItemView.displayEventTitle(eventItem.getTitle());
            listItemView.displayEventSubtitle(eventItem.getMeta().getSubtitle());
        }else{
            showError(listItemView, ERROR_NO_DATA_EVENT, listItemView.getEventPosition());
        }
    }

    private void applyDetailsDisplayEvent(EventItem eventItem) {
        if(!eventItem.getMeta().getImage().isEmpty() && !eventItem.getTitle().isEmpty() && !eventItem.getMeta().getSubtitle().isEmpty()){
            eventView.displayTitle(eventItem.getTitle());
            eventView.displaySubtitle(eventItem.getMeta().getSubtitle());
            eventView.displayTime(eventItem.getMeta().getTime());
            eventView.displayDate(eventItem.getMeta().getDate());
            eventView.displayDescription(eventItem.getMeta().getDescription());
            eventView.displayPrice(eventItem.getMeta().getPrice());
            eventView.displayImage(eventItem.getMeta().getImage());
        }else{
            showError(eventView, ERROR_NO_DATA_EVENT);
        }
    }

    @Override
    public void openDetailedView(int position) {
        EventItem item = repository.getEventByPosition(position);

        if(item == null || item.getTitle() == null || item.getTitle().trim().equals("")) {
            showError(listView, ERROR_EMPTY_EVENTS);
        }else{
            EventFragment eventFragment = new EventFragment();

            Bundle bundle = new Bundle();
            bundle.putSerializable("event", item);
            eventFragment.setArguments(bundle);

            if(listView != null) {
                listView.changeFragment(eventFragment);
            }
        }
    }

    @Override
    public int getEventsCountSize() {
        return repository.getAllEvents().size();
    }

    @Override
    public void shareEventContent() {
        eventView.buttonShare();
    }

    @Override
    public void notifyEvent() {
        eventView.buttonNotify();
    }

}
