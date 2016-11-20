package com.ayoprez.castro.mockito.arena;


import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.model.models.Arena;
import com.ayoprez.castro.model.models.ArenaMeta;
import com.ayoprez.castro.model.models.ArenaMetaCoordinates;
import com.ayoprez.castro.presenter.arena.ArenaPresenter;
import com.ayoprez.castro.presenter.arena.ArenaPresenterImpl;
import com.ayoprez.castro.model.repository.ArenaRepository;
import com.ayoprez.castro.ui.fragments.arena.ArenaView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.notNull;
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

    protected ArenaRepository mockRepository;
    protected ArenaView mockView;
    protected ArenaPresenter presenter;
    protected Arena arena;
    protected ArenaMeta arenaMeta;
    protected ArenaMetaCoordinates arenaMetaCoordinates;

    @Before
    public void setup(){
        mockRepository = mock(ArenaRepository.class);
        arena = new Arena();
        arenaMeta = new ArenaMeta();
        arenaMetaCoordinates = new ArenaMetaCoordinates();

        arena.setId(1);
        arena.setTitle("Huesas Arena");
        arenaMetaCoordinates.setAddress("C/ Hueso");
        arenaMetaCoordinates.setLat("19.0347540938");
        arenaMetaCoordinates.setLng("47.3883547866");
        arenaMeta.setCoordinates(arenaMetaCoordinates);
        arenaMeta.setDescription("Sumergete en la descripción");
        arenaMeta.setPhoto("www.url.es/mierda");
        arena.setMeta(arenaMeta);

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
        verify(mockView, never()).showErrorMessage(anyByte());
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoData() {

        when(mockRepository.getArena()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getArena();

        verify(mockView, times(1)).showErrorMessage(anyByte());
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
        verify(mockView, never()).showErrorMessage(anyByte());
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
        verify(mockView, times(1)).showErrorMessage(anyByte());
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
        presenter.loadArenaData();
    }

}
