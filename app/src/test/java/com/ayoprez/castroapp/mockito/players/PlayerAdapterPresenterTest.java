package com.ayoprez.castroapp.mockito.players;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.players.PlayerAdapterPresenterImpl;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.ui.viewholders.players.PlayerListItemView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayerAdapterPresenterTest {

    PlayersRepository mockRepository;
    PlayerListItemView mockView;
    PlayerAdapterPresenter presenter;
    PlayerItem item;
    ArrayList<PlayerItem> itemsList;

    @Before
    public void setUp(){
        mockRepository = mock(PlayersRepository.class);

        item = initPlayerItem(1, "www.img.com/1", "Name");

        itemsList = new ArrayList<>();
        itemsList.add(initPlayerItem(1, "www.img.com/1", "Name"));
        itemsList.add(initPlayerItem(2, "www.img.com/2", "Name2"));
        itemsList.add(initPlayerItem(3, "www.img.com/3", "Name3"));

        when(mockRepository.getPlayer(anyInt())).thenReturn(item);

        when(mockRepository.getAllPlayers()).thenReturn(itemsList);

        mockView = mock(PlayerListItemView.class);
        presenter = new PlayerAdapterPresenterImpl(mockRepository);
    }

    public PlayerItem initPlayerItem(int id, String image, String title){
        PlayerItem eventItem = new PlayerItem();
        eventItem.setId(id);
        eventItem.getMeta().setPhoto(image);
        eventItem.setTitle(title);
        return eventItem;
    }

    @Test
    public void shouldBeAbleToLoadThePlayerFromRepositoryWhenValidPlayerIsPresent(){
        when(mockView.getItemPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getPlayer(anyInt());
        verify(mockRepository, never()).getAllPlayers();

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, times(1)).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).displayItemTitle("Name");
        verify(mockView, never()).showError();
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenPlayerIsNotPresenter(){
        when(mockView.getItemPosition()).thenReturn(1);

        when(mockRepository.getPlayer(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getPlayer(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, never()).displayItemTitle("Name");
        verify(mockView, times(1)).showError();
    }

    @Test
    public void shouldShowErrorMessageWhenPlayerDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.setTitle("");

        when(mockRepository.getPlayer(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getPlayer(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, never()).displayItemTitle("Name");
        verify(mockView, times(1)).showError();
    }

    @Test
    public void shouldGetTotalNumberOfPlayers(){
        presenter.setView(mockView);

        assertEquals(presenter.getPlayersCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadPlayersData();
    }
}
