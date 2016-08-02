package com.ayoprez.castroapp.mockito.arena;


import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.Arena;
import com.ayoprez.castroapp.presenter.arena.ArenaPresenter;
import com.ayoprez.castroapp.presenter.arena.ArenaPresenterImpl;
import com.ayoprez.castroapp.repository.ArenaRepository;
import com.ayoprez.castroapp.ui.fragments.arena.ArenaView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 03.07.16.
 */
public class ArenaPresenterTests {
    private static final String TAG = ArenaPresenterTests.class.getSimpleName();

    ArenaRepository mockRepository;
    ArenaView mockView;
    ArenaPresenter presenter;
    Arena arena;

    @Before
    public void setup(){
        mockRepository = mock(ArenaRepository.class);
        arena = new Arena();

        arena.setName("Huesas Arena");
        arena.setAddress("C/ Hueso");
        arena.setDescription("Sumergete en la descripción");
        arena.setImage("www.url.es/mierda");
        when(mockRepository.getArena()).thenReturn(arena);

        mockView = mock(ArenaView.class);

        presenter = new ArenaPresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheArenaDataFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getArena();

        verify(mockView, times(1)).displayName("Huesas Arena");
        verify(mockView, times(1)).displayAddress("C/ Hueso");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).displayMap("C/ Hueso");
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoData() {

        when(mockRepository.getArena()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getArena();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayName("Huesas Arena");
        verify(mockView, never()).displayAddress("C/ Hueso");
        verify(mockView, never()).displayDescription("Sumergete en la descripción");
        verify(mockView, never()).displayImage("www.url.es/mierda");
        verify(mockView, never()).displayMap("C/ Hueso");
    }

    @Test
    public void shouldOpenMap() {
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getArena();

        presenter.openMap("C/ Hueso");

        verify(mockView, times(1)).displayName("Huesas Arena");
        verify(mockView, times(1)).displayAddress("C/ Hueso");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, times(1)).displayMap("C/ Hueso");
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldShowErrorMessageWhenOnMapAddressIsNull(){
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getArena();

        presenter.openMap(null);

        verify(mockView, times(1)).displayName("Huesas Arena");
        verify(mockView, times(1)).displayAddress("C/ Hueso");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).displayMap("C/ Hueso");
        verify(mockView, times(1)).showErrorMessage();
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
        presenter.loadArenaData();
    }

}
