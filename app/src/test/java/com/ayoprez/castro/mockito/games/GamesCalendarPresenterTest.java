package com.ayoprez.castro.mockito.games;


import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.CalendarItem;
import com.ayoprez.castro.models.CalendarItemMeta;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenter;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenterImpl;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.ui.fragments.games.GamesCalendarView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 17.08.16.
 */
public class GamesCalendarPresenterTest {

    GamesRepository mockRepository;
    GamesCalendarView mockView;
    GamesCalendarPresenter presenter;
    CalendarItem calendar;
    CalendarItemMeta calendarItemMeta;


    @Before
    public void setUp(){
        mockRepository = mock(GamesRepository.class);
        calendar = new CalendarItem();
        calendarItemMeta = new CalendarItemMeta();

        calendar.setId(1);
        calendarItemMeta.setCalendar("http://images.de/image");
        calendar.setMeta(calendarItemMeta);

        when(mockRepository.getCalendar()).thenReturn(calendar);

        mockView = mock(GamesCalendarView.class);

        presenter = new GamesCalendarPresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheCalendarFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).displayCalendar("http://images.de/image");
        verify(mockView, never()).showErrorMessage(anyByte());
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenCalendarIsNull() {

        when(mockRepository.getCalendar()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoImageInCalendar() {

        CalendarItem calendarItem = new CalendarItem();
        CalendarItemMeta calendarItemMeta = new CalendarItemMeta();
        calendarItem.setId(1);
        calendarItemMeta.setCalendar(null);
        calendarItem.setMeta(calendarItemMeta);

        when(mockRepository.getCalendar()).thenReturn(calendarItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoURLInCalendar() {

        CalendarItem calendarItem = new CalendarItem();
        CalendarItemMeta calendarItemMeta = new CalendarItemMeta();
        calendarItem.setId(1);
        calendarItemMeta.setCalendar("");
        calendarItem.setMeta(calendarItemMeta);

        when(mockRepository.getCalendar()).thenReturn(calendarItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageWhenRepositoryIsNull(){
        presenter = new GamesCalendarPresenterImpl(null);

        presenter.setView(mockView);

        verify(mockView, times(1)).showErrorMessage(anyByte());
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
    }
}
