package com.ayoprez.castroapp.mockito.games;


import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.CalendarItem;
import com.ayoprez.castroapp.presenter.games.GamesCalendarPresenter;
import com.ayoprez.castroapp.presenter.games.GamesCalendarPresenterImpl;
import com.ayoprez.castroapp.repository.GamesRepository;
import com.ayoprez.castroapp.ui.fragments.games.GamesCalendarView;

import org.junit.Before;
import org.junit.Test;

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


    @Before
    public void setUp(){
        mockRepository = mock(GamesRepository.class);
        calendar = new CalendarItem();

        calendar.setId(1);
        calendar.getMeta().setCalendar("http://images.de/image");

        when(mockRepository.getCalendar()).thenReturn(calendar);

        mockView = mock(GamesCalendarView.class);

        presenter = new GamesCalendarPresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheCalendarFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).displayCalendar("http://images.de/image");
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenCalendarIsNull() {

        when(mockRepository.getCalendar()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoImageInCalendar() {

        CalendarItem calendarItem = new CalendarItem();
        calendarItem.setId(1);
        calendarItem.getMeta().setCalendar(null);

        when(mockRepository.getCalendar()).thenReturn(calendarItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoURLInCalendar() {

        CalendarItem calendarItem = new CalendarItem();
        calendarItem.setId(1);
        calendarItem.getMeta().setCalendar("");

        when(mockRepository.getCalendar()).thenReturn(calendarItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getCalendar();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayCalendar("http://images.de/image");
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
    }
}
