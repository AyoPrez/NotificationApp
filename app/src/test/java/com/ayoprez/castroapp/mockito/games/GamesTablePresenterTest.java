package com.ayoprez.castroapp.mockito.games;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.TableItem;
import com.ayoprez.castroapp.presenter.games.GamesTablePresenter;
import com.ayoprez.castroapp.presenter.games.GamesTablePresenterImpl;
import com.ayoprez.castroapp.repository.GamesRepository;
import com.ayoprez.castroapp.ui.fragments.games.GamesTableView;

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
public class GamesTablePresenterTest {

    GamesRepository mockRepository;
    GamesTableView mockView;
    GamesTablePresenter presenter;
    TableItem table;

    @Before
    public void setUp(){
        mockRepository = mock(GamesRepository.class);
        table = new TableItem();

        table.setId(1);
        table.getMeta().setTabla("http://images.de/image");

        when(mockRepository.getTable()).thenReturn(table);

        mockView = mock(GamesTableView.class);

        presenter = new GamesTablePresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheCalendarFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).displayTable("http://images.de/image");
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenCalendarIsNull() {

        when(mockRepository.getTable()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoImageInCalendar() {

        TableItem tableItem = new TableItem();
        tableItem.setId(1);
        tableItem.getMeta().setTabla(null);

        when(mockRepository.getTable()).thenReturn(tableItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoURLInCalendar() {

        TableItem tableItem = new TableItem();
        tableItem.setId(1);
        tableItem.getMeta().setTabla("");

        when(mockRepository.getTable()).thenReturn(tableItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
    }
}
