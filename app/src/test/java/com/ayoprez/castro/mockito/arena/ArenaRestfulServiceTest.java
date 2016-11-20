package com.ayoprez.castro.mockito.arena;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.model.models.Arena;
import com.ayoprez.castro.model.models.ArenaMeta;
import com.ayoprez.castro.model.repository.ArenaRepository;
import com.ayoprez.castro.model.restful.ArenaRestfulService;
import com.ayoprez.castro.model.restful.ArenaRestfulServiceImpl;
import com.ayoprez.castro.model.restful.RestfulService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;

import retrofit2.Call;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 22.08.16.
 */
public class ArenaRestfulServiceTest {
    private static final String TAG = ArenaRestfulServiceTest.class.getSimpleName();

    private ArenaRestfulService presenter;
    private ArrayList<Arena> arena;

    @Mock
    CommonActivityView mockView;

    @Mock
    ArenaRepository mockRepository;

    @Mock
    RestfulService mockRestfulService;

    @Mock
    Call<ArrayList<Arena>> mockCall;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new ArenaRestfulServiceImpl(mockRepository, mockRestfulService);
        arena = new ArrayList<>(Collections.singletonList(new Arena()));
    }

//    @Test
//    public void shouldSuccess() throws Exception{
//        when(mockRestfulService.getArenaFromServer()).thenReturn(mockCall);
//        Response<ArrayList<Arena>> res = Response.success(arena);
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulArena(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, never()).showErrorMessage(anyByte());
//        verify(mockRepository, times(1)).saveArena(res.body().get(0));
//    }
//
//    @Test
//    public void shouldFailInSuccess() throws Exception{
//        when(mockRestfulService.getArenaFromServer()).thenReturn(mockCall);
//        Response<ArrayList<Arena>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulArena(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, times(1)).showErrorMessage(anyByte());
//        verify(mockRepository, never()).saveArena((Arena) anyObject());
//    }

    @Test
    public void shouldNotDeleteAllIfThereIsNothing(){
        when(mockRepository.getArena()).thenReturn(null);

        verify(mockRepository, never()).deleteArena();
    }

    @Test
    public void shouldDeleteAllWhenThereIsSomething(){
        ArrayList<Arena> arenaItems = new ArrayList<>();
        Arena arenaItem = new Arena();
        ArenaMeta arenaItemMeta = new ArenaMeta();
        arenaItemMeta.setDescription("");
        arenaItem.setTitle("");
        arenaItem.setMeta(arenaItemMeta);
        arenaItems.add(arenaItem);

        when(mockRepository.getArena()).thenReturn(arenaItem);

        presenter = new ArenaRestfulServiceImpl(mockRepository, mockRestfulService);

        verify(mockRepository, times(1)).deleteArena();
    }
}
