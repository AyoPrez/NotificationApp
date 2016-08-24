package com.ayoprez.castro.mockito.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.models.PlayerItemMeta;
import com.ayoprez.castro.presenter.players.PlayersPresenter;
import com.ayoprez.castro.presenter.players.PlayersPresenterImpl;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.ui.fragments.players.PlayersView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.anyString;
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
    PlayerItemMeta playerItemMeta;
    PlayersRepository repository;

    @Before
    public void setup(){
        item = new PlayerItem();
        playerItemMeta = new PlayerItemMeta();

        repository = mock(PlayersRepository.class);

        item.setId(1);
        item.setTitle("PlayerName");
        playerItemMeta.setPhoto("http://www.image.de/dedede");
        playerItemMeta.setCategory("Senior");
        item.setMeta(playerItemMeta);

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

        verify(mockView, times(1)).showEmptyListMessage(anyString());
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereArePlayersInRepository(){
        ArrayList<PlayerItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllPlayers()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage(anyString());
        verify(mockView, times(1)).initRecyclerView();
    }
}
