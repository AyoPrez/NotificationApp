package com.ayoprez.castroapp.mockito.restful;

import android.test.suitebuilder.annotation.SmallTest;

import com.ayoprez.castroapp.common.CommonActivityView;
import com.ayoprez.castroapp.models.EventItem;
import com.ayoprez.castroapp.repository.EventsRepository;
import com.ayoprez.castroapp.restful.EventsRestfulService;
import com.ayoprez.castroapp.restful.EventsRestfulServiceImpl;
import com.ayoprez.castroapp.restful.RestfulService;
import com.ayoprez.castroapp.ui.SplashView;

import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okhttp3.internal.http.RealResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 20.08.16.
 */
public class EventsRestfulServiceTest {
    private static final String TAG = EventsRestfulServiceTest.class.getSimpleName();

    private EventsRestfulService presenter;
    private ArrayList<EventItem> eventItems;

    @Mock
    CommonActivityView mockView;

    @Mock
    EventsRepository mockRepository;

    @Mock
    RestfulService mockRestfulService;

    @Mock
    Call<ArrayList<EventItem>> mockCall;

    @Captor
    ArgumentCaptor<Callback<ArrayList<EventItem>>> argumentCaptor;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new EventsRestfulServiceImpl(mockRepository, mockRestfulService);
        eventItems = new ArrayList<>(Collections.singletonList(new EventItem()));
    }

    @Test
    public void shouldSuccess(){
        when(mockRestfulService.getEventsFromServer()).thenReturn(mockCall);
        Response<ArrayList<EventItem>> res = Response.success(eventItems);
        presenter.getRestfulEvents(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verify(mockView, never()).showErrorMessage();
        verify(mockRepository, times(1)).saveEvents(res.body());
    }

    @Test
    public void shouldFailInSuccess(){
        when(mockRestfulService.getEventsFromServer()).thenReturn(mockCall);
        Response<ArrayList<EventItem>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
        presenter.getRestfulEvents(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onResponse(null, res);

        verify(mockView, times(1)).showErrorMessage();
        verify(mockRepository, never()).saveEvents(res.body());
    }

    @Test
    public void shouldFail(){
        when(mockRestfulService.getEventsFromServer()).thenReturn(mockCall);
        Throwable throwable = new Throwable(new RuntimeException());

        presenter.getRestfulEvents(mockView);

        verify(mockCall).enqueue(argumentCaptor.capture());
        argumentCaptor.getValue().onFailure(null, throwable);

        verify(mockView, times(1)).showErrorMessage();
    }

}
