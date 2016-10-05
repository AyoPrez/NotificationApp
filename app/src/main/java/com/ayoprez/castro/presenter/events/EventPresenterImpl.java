package com.ayoprez.castro.presenter.events;

import android.content.SharedPreferences;
import android.text.Html;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.NotificationEvents;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.ui.fragments.events.EventFragment;
import com.ayoprez.castro.ui.fragments.events.EventListView;
import com.ayoprez.castro.ui.fragments.events.EventView;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterImpl extends ErrorManager implements EventPresenter, EventAdapterPresenter {

    private EventListView listView;
    private EventListItemView listItemView;
    private EventView eventView;
    private EventItem eventItem;
    protected EventsRepository repository;
    protected NotificationEventsRepository notificationEventsRepository;

    public EventPresenterImpl(EventsRepository eventsRepository, NotificationEventsRepository notificationEventsRepository) {
        this.repository = eventsRepository;
        this.notificationEventsRepository = notificationEventsRepository;
    }

    @Override
    public void setView(EventListView view) {
        if(view != null) {
            listView = view;
        }

        if(listView == null){
            throw new ViewNotFoundException();
        }else{
            try {
                listView.initRecyclerView();
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
    public void setEventView(EventView eventView, int eventId) {
        this.eventView = eventView;

        eventItem = repository.getEvent(eventId);

        if(eventItem != null) {
            applyDetailsDisplayEvent(eventItem);
        }else{
            showError(listItemView, ERROR_EMPTY_EVENTS, eventId);
        }
    }

    @Override
    public void loadEventData() {
        if(listItemView == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = listItemView.getEventPosition();
        eventItem = repository.getEventByPosition(itemPosition);

        if(eventItem == null){
            showError(listItemView, ERROR_EMPTY_EVENTS, itemPosition);
        }else{
            applyListDisplayEvents(eventItem);
        }
    }

    private void applyListDisplayEvents(EventItem eventItem) {
        if(!eventItem.getMeta().getImage().isEmpty() && !eventItem.getTitle().isEmpty()){
            listItemView.displayEventImage(eventItem.getMeta().getImage());
            listItemView.displayEventTitle(eventItem.getTitle());
            listItemView.displayEventSubtitle(eventItem.getMeta().getSubtitle());
        }else{
            showError(listItemView, ERROR_NO_DATA_EVENT, listItemView.getEventPosition());
        }
    }

    private void applyDetailsDisplayEvent(EventItem eventItem) {
        if(!eventItem.getMeta().getImage().isEmpty() && !eventItem.getTitle().isEmpty()){
            eventView.displayTitle(eventItem.getTitle());
            eventView.displaySubtitle(eventItem.getMeta().getSubtitle());
            eventView.displayTime(eventItem.getMeta().getTime());
            eventView.displayDate(eventItem.getMeta().getDate());

            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
                eventView.displayDescription(Html.fromHtml(eventItem.getMeta().getDescription(), Html.FROM_HTML_MODE_LEGACY).toString());
            } else {
                eventView.displayDescription(Html.fromHtml(eventItem.getMeta().getDescription()).toString());
            }

            eventView.displayPrice(eventItem.getMeta().getPrice());
            eventView.displayImage(eventItem.getMeta().getImage());
            eventView.buttonNotify();
            eventView.buttonShare();
            getNotificationButtonState(eventItem.getId());
        }else{
            showError(eventView, ERROR_NO_DATA_EVENT);
        }
    }

    @Override
    public void openDetailedView(EventListView listView, int position) {
        EventItem item = repository.getEventByPosition(position);

        if(item == null || item.getTitle() == null || item.getTitle().trim().equals("")) {
            showError(listView, ERROR_EMPTY_EVENTS);
        }else{
            EventFragment eventFragment = new EventFragment();

            if(listView != null) {
                listView.changeFragment(eventFragment, item.getId());
            }
        }
    }

    @Override
    public int getEventsCountSize(EventListView listView) {
        if(repository.getAllEvents().size() == 0){
            listView.showEmptyListMessage(ERROR_EMPTY_EVENTS);
        }

        return repository.getAllEvents().size();
    }

    @Override
    public void shareEventContent(int id) {
        EventItem eventItem = repository.getEvent(id);

        HashMap<String, String> hashMapEventData = new HashMap<>();

        hashMapEventData.put("DATE", eventItem.getMeta().getDate());
        hashMapEventData.put("TIME", eventItem.getMeta().getTime());
        hashMapEventData.put("TITLE", eventItem.getTitle());
        hashMapEventData.put("PRICE", eventItem.getMeta().getPrice());

        eventView.shareContent(hashMapEventData);
    }

    @Override
    public void notifyEvent(int id) {
        EventItem eventItem = repository.getEvent(id);
        eventView.notifyAlarmEvent(eventItem.getMeta().getDate(), eventItem.getMeta().getTime(), eventItem.getTitle());
    }

    @Override
    public void confirmScheduledEvent(int eventId) {
        EventItem event = repository.getEvent(eventId);
        notificationEventsRepository.saveNotificationEvents(event);

        eventView.confirmNotification();

        getNotificationButtonState(eventId);
    }

    private void getNotificationButtonState(int eventId){
        boolean state = notificationEventsRepository.isEventScheduled(eventId);

        eventView.displayNotificationButtonState(state);
    }

}
