package com.ayoprez.castroapp.mockito.players;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.presenter.players.PlayersPresenter;
import com.ayoprez.castroapp.presenter.players.PlayersPresenterImpl;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.ui.fragments.players.PlayersView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 26.06.16.
 */
public class PlayerPresenterTests {

    PlayersView mockView;
    PlayersPresenter presenter;
    PlayerItem item;
    PlayersRepository repository;

    @Before
    public void setup(){
        item = new PlayerItem();

        repository = mock(PlayersRepository.class);

        item.setId(1);
        item.setTitle("PlayerName");

        mockView = mock(PlayersView.class);

        presenter = new PlayersPresenterImpl(repository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);
    }

    @Test
    public void shouldShowEmptyListMessageWhenNoPlayersInRepository(){
        ArrayList<PlayerItem> items = new ArrayList<>();
        when(repository.getAllPlayers()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereArePlayersInRepository(){
        ArrayList<PlayerItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllPlayers()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
    }
}
