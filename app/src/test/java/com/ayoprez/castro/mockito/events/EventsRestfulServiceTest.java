package com.ayoprez.castro.mockito.events;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.EventItemMeta;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.restful.EventsRestfulService;
import com.ayoprez.castro.restful.EventsRestfulServiceImpl;
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

import static junit.framework.Assert.assertEquals;
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


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new EventsRestfulServiceImpl(mockRepository, mockRestfulService);
        eventItems = new ArrayList<>(Collections.singletonList(new EventItem()));
    }

//    @Test
//    public void shouldSuccess() throws Exception{
//        when(mockRestfulService.getEventsFromServer()).thenReturn(mockCall);
//        Response<ArrayList<EventItem>> res = Response.success(eventItems);
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulEvents(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, never()).showErrorMessage(anyByte());
//        verify(mockRepository, times(1)).saveEvents(res.body());
//    }
//
//    @Test
//    public void shouldFailInSuccess() throws Exception{
//        when(mockRestfulService.getEventsFromServer()).thenReturn(mockCall);
//        Response<ArrayList<EventItem>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulEvents(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, times(1)).showErrorMessage(anyByte());
//        verify(mockRepository, never()).saveEvents(res.body());
//    }

    @Test
    public void shouldNotDeleteAllIfThereIsNothing(){
        ArrayList<EventItem> eventItems = new ArrayList<>();
        when(mockRepository.getAllEvents()).thenReturn(eventItems);

        verify(mockRepository, never()).deleteAllEvents();
    }

    @Test
    public void shouldDeleteAllWhenThereIsSomething(){
        ArrayList<EventItem> eventItems = new ArrayList<>();
        EventItem eventItem = new EventItem();
        EventItemMeta eventItemMeta = new EventItemMeta();
        eventItemMeta.setDate("");
        eventItem.setTitle("");
        eventItem.setMeta(eventItemMeta);
        eventItems.add(eventItem);

        when(mockRepository.getAllEvents()).thenReturn(eventItems);

        presenter = new EventsRestfulServiceImpl(mockRepository, mockRestfulService);

        verify(mockRepository, times(1)).deleteAllEvents();
    }

//    @Test
//    public void shouldSortTheEventsByDate(){
//        EventItem eventItem = new EventItem();
//        EventItemMeta eventItemMeta = new EventItemMeta();
//        eventItemMeta.setDate("Lunes 26 de Septiembre de 2016");
//        eventItem.setMeta(eventItemMeta);
//
//        EventItem eventItem2 = new EventItem();
//        EventItemMeta eventItemMeta2 = new EventItemMeta();
//        eventItemMeta2.setDate("Lunes 3 de Octubre de 2016");
//        eventItem2.setMeta(eventItemMeta2);
//
//        ArrayList<EventItem> eventItemsList = new ArrayList<>();
//
//        eventItemsList.add(eventItem);
//        eventItemsList.add(eventItem2);
//
//        presenter.getRestfulEvents(mockView);
//
//
//    }
}
