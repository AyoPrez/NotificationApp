package com.ayoprez.castro.mockito.players;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.models.PlayerItemMeta;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenterImpl;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.ui.viewholders.players.PlayerListItemView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
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

        item = initPlayerItem(1, "www.img.com/1", "Name", "Senior");

        itemsList = new ArrayList<>();
        itemsList.add(initPlayerItem(1, "www.img.com/1", "Name", "Senior"));
        itemsList.add(initPlayerItem(2, "www.img.com/2", "Name2", "Junior"));
        itemsList.add(initPlayerItem(3, "www.img.com/3", "Name3", "Amateur"));

        when(mockRepository.getPlayer(anyInt())).thenReturn(item);

        when(mockRepository.getAllPlayers()).thenReturn(itemsList);

        mockView = mock(PlayerListItemView.class);
        presenter = new PlayerAdapterPresenterImpl(mockRepository);
    }

    public PlayerItem initPlayerItem(int id, String image, String title, String category){
        PlayerItem playerItem = new PlayerItem();
        PlayerItemMeta playerItemMeta = new PlayerItemMeta();
        playerItem.setId(id);
        playerItem.setTitle(title);
        playerItemMeta.setPhoto(image);
        playerItemMeta.setCategory(category);
        playerItem.setMeta(playerItemMeta);
        return playerItem;
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
        verify(mockView, never()).showErrorMessage(anyString(), anyInt());
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
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenPlayerDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.setTitle("");

        when(mockRepository.getPlayer(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getPlayer(anyInt());

        verify(mockView, times(2)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, never()).displayItemTitle("Name");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
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
