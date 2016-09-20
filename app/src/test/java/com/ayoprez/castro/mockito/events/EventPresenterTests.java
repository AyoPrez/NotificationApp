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
import com.ayoprez.castro.ui.fragments.events.EventListView;
import com.ayoprez.castro.ui.fragments.events.EventView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by ayo on 26.06.16.
 */
public class EventPresenterTests {

    EventListView mockView;
    EventPresenter presenter;
    EventAdapterPresenter eventAdapterPresenter;
    EventItem item;
    EventItemMeta itemMeta;
    EventsRepository mockRepository;

    @Before
    public void setup(){
        item = new EventItem();
        itemMeta = new EventItemMeta();

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

        presenter = new EventPresenterImpl(mockRepository);
        eventAdapterPresenter = new EventPresenterImpl(mockRepository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        eventAdapterPresenter.openDetailedView(item.getId());
    }

    @Test
    public void shouldShowErrorMessageWhenItemIsNull(){
        presenter.setView(mockView);
        eventAdapterPresenter.openDetailedView(-1);

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }
//        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsNull(){

        item.setTitle(null);

        presenter.setView(mockView);
        eventAdapterPresenter.openDetailedView(item.getId());

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }
//        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldShowErrorMessageWhenItemTitleIsEmpty(){

        item.setTitle("");

        presenter.setView(mockView);
        eventAdapterPresenter.openDetailedView(item.getId());

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }
//        verify(mockView, never()).changeFragment(any(Fragment.class));
    }

    @Test
    public void shouldGiveCorrectDate(){

    }
}
