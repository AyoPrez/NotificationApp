package com.ayoprez.castro.mockito.games;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.TableItem;
import com.ayoprez.castro.models.TableItemMeta;
import com.ayoprez.castro.presenter.games.GamesTablePresenter;
import com.ayoprez.castro.presenter.games.GamesTablePresenterImpl;
import com.ayoprez.castro.repository.GamesRepository;
import com.ayoprez.castro.ui.fragments.games.GamesTableView;

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
public class GamesTablePresenterTest {

    GamesRepository mockRepository;
    GamesTableView mockView;
    GamesTablePresenter presenter;
    TableItem table;
    TableItemMeta tableItemMeta;

    @Before
    public void setUp(){
        mockRepository = mock(GamesRepository.class);
        table = new TableItem();
        tableItemMeta = new TableItemMeta();

        table.setId(1);
        tableItemMeta.setTabla("http://images.de/image");
        table.setMeta(tableItemMeta);

        when(mockRepository.getTable()).thenReturn(table);

        mockView = mock(GamesTableView.class);

        presenter = new GamesTablePresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheCalendarFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).displayTable("http://images.de/image");
        verify(mockView, never()).showErrorMessage(anyByte());
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenCalendarIsNull() {

        when(mockRepository.getTable()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoImageInCalendar() {

        TableItem tableItem = new TableItem();
        tableItem.setId(1);
        tableItemMeta.setTabla(null);
        tableItem.setMeta(tableItemMeta);

        when(mockRepository.getTable()).thenReturn(tableItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoURLInCalendar() {

        TableItem tableItem = new TableItem();
        tableItem.setId(1);
        tableItemMeta.setTabla("");
        tableItem.setMeta(tableItemMeta);

        when(mockRepository.getTable()).thenReturn(tableItem);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getTable();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockView, never()).displayTable("http://images.de/image");
    }

    @Test
    public void shouldShowErrorMessageWhenRepositoryIsNull(){
        presenter = new GamesTablePresenterImpl(null);

        presenter.setView(mockView);

        verify(mockView, times(1)).showErrorMessage(anyByte());
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
    }
}
