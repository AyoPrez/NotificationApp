package com.ayoprez.castroapp.mockito.events;

import android.support.v4.app.Fragment;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.presenter.events.EventPresenter;
import com.ayoprez.castroapp.presenter.events.EventPresenterImpl;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.ui.fragments.events.EventView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterTests {

    EventView mockView;
    EventPresenter presenter;
    EventItem item;

    @Before
    public void setup(){
        item = new EventItem();

        item.setId(1);
        item.setTitle("EventName");
        item.setSubtitle("EventSubtitle");

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

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsNull(){

        item.setTitle(null);

        presenter.setView(mockView);
        presenter.openDetailedView(item);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsEmpty(){

        item.setTitle("");

        presenter.setView(mockView);
        presenter.openDetailedView(item);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
        verify(mockView, never()).changeFragment(any(Fragment.class));
    }
}
