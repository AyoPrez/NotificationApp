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
import java.util.Collections;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyCollectionOf;
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

    PlayerListItemView mockView;
    PlayerAdapterPresenter presenter;
    PlayerItem item;
    ArrayList<PlayerItem> itemsList;

    @Before
    public void setUp(){

        item = initPlayerItem(1, "www.img.com/1", "Name", "Senior");

        itemsList = new ArrayList<>();
        itemsList.add(initPlayerItem(1, "www.img.com/1", "Name", "Senior"));
        itemsList.add(initPlayerItem(2, "www.img.com/2", "Name2", "Junior"));
        itemsList.add(initPlayerItem(3, "www.img.com/3", "Name3", "Amateur"));

        mockView = mock(PlayerListItemView.class);
        presenter = new PlayerAdapterPresenterImpl();
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

        presenter.setView(mockView);
        presenter.loadPlayersData(itemsList, anyInt());

        verify(mockView, times(1)).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).displayItemTitle("Name");
        verify(mockView, never()).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenPlayerIsNotPresenter(){

        presenter.setView(mockView);
        presenter.loadPlayersData(new ArrayList<PlayerItem>(), 0);

        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, never()).displayItemTitle("Name");
        verify(mockView, times(1)).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenPlayerDataAreEmpty(){

        item.setTitle("");
        itemsList.clear();
        itemsList.add(item);

        presenter.setView(mockView);
        presenter.loadPlayersData(itemsList, 0);

        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, never()).displayItemTitle("Name");
        verify(mockView, times(1)).showErrorMessage(anyByte(), anyInt());
    }

    @Test
    public void shouldGetTotalNumberOfPlayers(){
        presenter.setView(mockView);
        presenter.loadPlayersData(itemsList, anyInt());

        assertEquals(itemsList.size(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadPlayersData(new ArrayList<PlayerItem>(), 0);
    }
}
