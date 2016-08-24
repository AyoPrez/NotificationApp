package com.ayoprez.castro.mockito.events;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenterImpl;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 10.07.16.
 */
public class EventAdapterPresenterTest {

    EventsRepository mockRepository;
    EventListItemView mockView;
    EventAdapterPresenter presenter;
    EventItem item;
    ArrayList<EventItem> itemsList;

    @Before
    public void setUp(){
        mockRepository = mock(EventsRepository.class);

        item = initEventItem(1, "www.img.com/1", "Title", "Subtitle", "Party1", "Mañana", "Noche");

        itemsList = new ArrayList<>();
        itemsList.add(initEventItem(1, "www.img.com/1", "Title", "Subtitle", "Party1", "Miércoles", "22:00"));
        itemsList.add(initEventItem(2, "www.img.com/2", "Title2", "Subtitle2", "Party2", "Miércoles", "22:00"));
        itemsList.add(initEventItem(3, "www.img.com/3", "Title3", "Subtitle3","Party3", "Jueves", "23:00"));

        when(mockRepository.getEvent(anyInt())).thenReturn(item);

        when(mockRepository.getAllEvents()).thenReturn(itemsList);

        mockView = mock(EventListItemView.class);
        presenter = new EventAdapterPresenterImpl(mockRepository);
    }

    public EventItem initEventItem(int id, String image, String title, String subtitle, String description, String date, String time){
        EventItem eventItem = new EventItem();
        EventItemMeta eventItemMeta = new EventItemMeta();

        eventItem.setId(id);
        eventItem.setTitle(title);
        eventItemMeta.setDescription(description);
        eventItemMeta.setDate(date);
        eventItemMeta.setTime(time);
        eventItemMeta.setImage(image);
        eventItemMeta.setSubtitle(subtitle);
        eventItem.setMeta(eventItemMeta);
        return eventItem;
    }

    @Test
    public void shouldBeAbleToLoadTheEventFromRepositoryWhenValidEventIsPresent(){
        when(mockView.getEventPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getEvent(anyInt());
        verify(mockRepository, never()).getAllEvents();

        verify(mockView, times(1)).getEventPosition();
        verify(mockView, times(1)).displayEventImage("www.img.com/1");
        verify(mockView, times(1)).displayEventTitle("Title");
        verify(mockView, times(1)).displayEventSubtitle("Subtitle");
        verify(mockView, never()).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenEventIsNotPresenter(){
        when(mockView.getEventPosition()).thenReturn(1);

        when(mockRepository.getEvent(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getEvent(anyInt());

        verify(mockView, times(1)).getEventPosition();
        verify(mockView, never()).displayEventImage("www.img.com/1");
        verify(mockView, never()).displayEventTitle("Title");
        verify(mockView, never()).displayEventSubtitle("Subtitle");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenEventDataAreEmpty(){
        when(mockView.getEventPosition()).thenReturn(1);

        item.setTitle("");

        when(mockRepository.getEvent(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getEvent(anyInt());

        verify(mockView, times(2)).getEventPosition();
        verify(mockView, never()).displayEventImage("www.img.com/1");
        verify(mockView, never()).displayEventTitle("Title");
        verify(mockView, never()).displayEventSubtitle("Subtitle");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldGetTotalNumberOfEvents(){
        presenter.setView(mockView);

        assertEquals(presenter.getEventsCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadEventData();
    }
}