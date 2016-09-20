package com.ayoprez.castro.mockito.events;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.events.EventPresenterImpl;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.ui.viewholders.events.EventListItemView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyShort;
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

        item = initEventItem((short)1, "www.img.com/1", "Title", "Subtitle", "Party1", "Mañana", "Noche");

        itemsList = new ArrayList<>();
        itemsList.add(initEventItem((short)1, "www.img.com/1", "Title", "Subtitle", "Party1", "Miércoles", "22:00"));
        itemsList.add(initEventItem((short)2, "www.img.com/2", "Title2", "Subtitle2", "Party2", "Miércoles", "22:00"));
        itemsList.add(initEventItem((short)3, "www.img.com/3", "Title3", "Subtitle3","Party3", "Jueves", "23:00"));

        when(mockRepository.getEvent(anyShort())).thenReturn(item);

        when(mockRepository.getAllEvents()).thenReturn(itemsList);

        mockView = mock(EventListItemView.class);
        presenter = new EventPresenterImpl(mockRepository);
    }

    public EventItem initEventItem(short id, String image, String title, String subtitle, String description, String date, String time){
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

        presenter.setListItemView(mockView);

        verify(mockRepository, times(1)).getEvent(anyShort());
        verify(mockRepository, never()).getAllEvents();

        verify(mockView, times(1)).getEventPosition();
        verify(mockView, times(1)).displayEventImage("www.img.com/1");
        verify(mockView, times(1)).displayEventTitle("Title");
        verify(mockView, times(1)).displayEventSubtitle("Subtitle");
        verify(mockView, never()).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenEventIsNotPresenter(){
        when(mockView.getEventPosition()).thenReturn(1);

        when(mockRepository.getEvent(anyShort())).thenReturn(null);
        presenter.setListItemView(mockView);

        verify(mockRepository, times(1)).getEvent(anyShort());

        verify(mockView, times(1)).getEventPosition();
        verify(mockView, never()).displayEventImage("www.img.com/1");
        verify(mockView, never()).displayEventTitle("Title");
        verify(mockView, never()).displayEventSubtitle("Subtitle");
        verify(mockView, times(1)).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenEventDataAreEmpty(){
        when(mockView.getEventPosition()).thenReturn(1);

        item.setTitle("");

        when(mockRepository.getEvent(anyShort())).thenReturn(item);

        presenter.setListItemView(mockView);

        verify(mockRepository, times(1)).getEvent(anyShort());

        verify(mockView, times(2)).getEventPosition();
        verify(mockView, never()).displayEventImage("www.img.com/1");
        verify(mockView, never()).displayEventTitle("Title");
        verify(mockView, never()).displayEventSubtitle("Subtitle");
        verify(mockView, times(1)).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldGetTotalNumberOfEvents(){
        presenter.setListItemView(mockView);

        assertEquals(presenter.getEventsCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setListItemView((EventListItemView) null);

        presenter.loadEventData();
    }
}
