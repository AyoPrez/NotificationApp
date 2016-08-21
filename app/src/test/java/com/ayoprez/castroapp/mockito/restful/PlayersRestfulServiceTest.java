package com.ayoprez.castroapp.mockito.restful;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.PlayerItem;
import com.ayoprez.castroapp.repository.PlayersRepository;
import com.ayoprez.castroapp.restful.PlayerRestfulService;
import com.ayoprez.castroapp.restful.PlayerRestfulServiceImpl;
import com.ayoprez.castroapp.restful.RestfulService;
import com.ayoprez.castroapp.ui.SplashView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    @Captor
    ArgumentCaptor<Callback<ArrayList<PlayerItem>>> argumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new PlayerRestfulServiceImpl(mockRepository, mockRestfulService);
        eventItems = new ArrayList<>(Collections.singletonList(new PlayerItem()));
    }

    @Test
    public void shouldSuccess(){
        when(mockRestfulService.getPlayersFromServer()).thenReturn(mockCall);
        Response<ArrayList<PlayerItem>> res = Response.success(eventItems);
        presenter.getRestfulPlayers(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verify(mockView, never()).showErrorMessage();
        verify(mockRepository, times(1)).savePlayers(res.body());
    }

    @Test
    public void shouldFailInSuccess(){
        when(mockRestfulService.getPlayersFromServer()).thenReturn(mockCall);
        Response<ArrayList<PlayerItem>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
        presenter.getRestfulPlayers(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verify(mockView, times(1)).showErrorMessage();
        verify(mockRepository, never()).savePlayers(res.body());
    }

    @Test
    public void shouldFail(){
        when(mockRestfulService.getPlayersFromServer()).thenReturn(mockCall);
        Throwable throwable = new Throwable(new RuntimeException());

        presenter.getRestfulPlayers(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, throwable);

        verify(mockView, times(1)).showErrorMessage();
    }

}
