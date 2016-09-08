package com.ayoprez.castro.mockito.events;

import android.support.v4.app.Fragment;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;
import com.ayoprez.castro.presenter.events.EventPresenter;
import com.ayoprez.castro.presenter.events.EventPresenterImpl;
import com.ayoprez.castro.ui.fragments.events.EventView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterTests {

    EventView mockView;
    EventPresenter presenter;
    EventItem item;
    EventItemMeta itemMeta;

    @Before
    public void setup(){
        item = new EventItem();
        itemMeta = new EventItemMeta();

        item.setId(1);
        item.setTitle("EventName");
        itemMeta.setDescription("Party en algún place");
        itemMeta.setImage("http://www.attempt.de/ss");
        itemMeta.setDate("Miércoles 02 de Agosto de 2016");
        itemMeta.setTime("21:00");
        itemMeta.setSubtitle("EventSubtitle");
        item.setMeta(itemMeta);

        mockView = mock(EventView.class);

        presenter = new EventPresenterImpl();
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.openDetailedView(item);
    }

    @Test
    public void shouldShowErrorMessageWhenItemIsNull(){
        presenter.setView(mockView);
        presenter.openDetailedView(null);

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsNull(){

        item.setTitle(null);

        presenter.setView(mockView);
        presenter.openDetailedView(item);

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsEmpty(){

        item.setTitle("");

        presenter.setView(mockView);
        presenter.openDetailedView(item);

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }
}
