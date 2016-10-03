package com.ayoprez.castro.mockito.events;

import android.support.v4.app.Fragment;
import android.support.v4.media.MediaMetadataCompat;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.CommonListItemView;
import com.ayoprez.castro.common.CommonListView;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.events.EventPresenter;
import com.ayoprez.castro.presenter.events.EventPresenterImpl;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.ui.fragments.events.EventListView;
import com.ayoprez.castro.ui.fragments.events.EventView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyShort;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterTests {

    private EventListView mockView;
    private EventPresenter presenter;
    private EventAdapterPresenter eventAdapterPresenter;
    private EventItem item;
    private EventsRepository mockRepository;
    private NotificationEventsRepository mockNotificationRepository;

    @Before
    public void setup(){
        item = new EventItem();
        EventItemMeta itemMeta = new EventItemMeta();

        item.setId((short)1);
        item.setTitle("EventName");
        itemMeta.setDescription("Party en algún place");
        itemMeta.setImage("http://www.attempt.de/ss");
        itemMeta.setDate("Miércoles 02 de Agosto de 2016");
        itemMeta.setTime("21:00");
        itemMeta.setSubtitle("EventSubtitle");
        item.setMeta(itemMeta);

        mockView = mock(EventListView.class);
        mockRepository = mock(EventsRepository.class);
        mockNotificationRepository = mock(NotificationEventsRepository.class);

        presenter = new EventPresenterImpl(mockRepository, mockNotificationRepository);
        eventAdapterPresenter = new EventPresenterImpl(mockRepository, mockNotificationRepository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        eventAdapterPresenter.openDetailedView(mockView, item.getId());
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsNull() throws Exception{

        item.setTitle(null);

        presenter.setView(mockView);
        eventAdapterPresenter.openDetailedView(mockView, item.getId());

        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsEmpty() throws Exception{

        item.setTitle("");

        presenter.setView(mockView);
        eventAdapterPresenter.openDetailedView(mockView, item.getId());

        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
//        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

//    @Test
//    public void shouldGiveCorrectDate(){
//
//    }
}
