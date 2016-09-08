package com.ayoprez.castro.mockito.players;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.models.PlayerItem;
import com.ayoprez.castro.models.PlayerItemMeta;
import com.ayoprez.castro.repository.PlayersRepository;
import com.ayoprez.castro.restful.PlayerRestfulService;
import com.ayoprez.castro.restful.PlayerRestfulServiceImpl;
import com.ayoprez.castro.restful.RestfulService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;

import static org.mockito.Matchers.anyByte;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 20.08.16.
 */
public class PlayersRestfulServiceTest {
    private static final String TAG = PlayersRestfulServiceTest.class.getSimpleName();

    private PlayerRestfulService presenter;
    private ArrayList<PlayerItem> eventItems;

    @Mock
    CommonActivityView mockView;

    @Mock
    PlayersRepository mockRepository;

    @Mock
    RestfulService mockRestfulService;

    @Mock
    Call<ArrayList<PlayerItem>> mockCall;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new PlayerRestfulServiceImpl(mockRepository, mockRestfulService);
        eventItems = new ArrayList<>(Collections.singletonList(new PlayerItem()));
    }

    @Test
    public void shouldSuccess() throws Exception{
        when(mockRestfulService.getPlayersFromServer()).thenReturn(mockCall);
        Response<ArrayList<PlayerItem>> res = Response.success(eventItems);
        when(mockRestfulService.getPlayersFromServer().execute()).thenReturn(res);
        presenter.getRestfulPlayers(mockView);

        verify(mockCall).execute();

        verify(mockView, never()).showErrorMessage(anyByte());
        verify(mockRepository, times(1)).savePlayers(res.body());
    }

    @Test
    public void shouldFailInSuccess() throws Exception{
        when(mockRestfulService.getPlayersFromServer()).thenReturn(mockCall);
        Response<ArrayList<PlayerItem>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
        when(mockRestfulService.getPlayersFromServer().execute()).thenReturn(res);
        presenter.getRestfulPlayers(mockView);

        verify(mockCall).execute();

        verify(mockView, times(1)).showErrorMessage(anyByte());
        verify(mockRepository, never()).savePlayers(res.body());
    }

    @Test
    public void shouldNotDeleteAllIfThereIsNothing(){
        ArrayList<PlayerItem> eventItems = new ArrayList<>();
        when(mockRepository.getAllPlayers()).thenReturn(eventItems);

        verify(mockRepository, never()).deleteAllPlayers();
    }

    @Test
    public void shouldDeleteAllWhenThereIsSomething(){
        ArrayList<PlayerItem> eventItems = new ArrayList<>();
        PlayerItem eventItem = new PlayerItem();
        PlayerItemMeta eventItemMeta = new PlayerItemMeta();
        eventItemMeta.setCategory("");
        eventItem.setTitle("");
        eventItem.setMeta(eventItemMeta);
        eventItems.add(eventItem);

        when(mockRepository.getAllPlayers()).thenReturn(eventItems);

        presenter = new PlayerRestfulServiceImpl(mockRepository, mockRestfulService);

        verify(mockRepository, times(1)).deleteAllPlayers();
    }
}
