package com.ayoprez.castro.mockito.aboutUs;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.model.models.AboutUs;
import com.ayoprez.castro.model.models.AboutUsMeta;
import com.ayoprez.castro.model.repository.AboutUsRepository;
import com.ayoprez.castro.model.restful.AboutUsRestfulService;
import com.ayoprez.castro.model.restful.AboutUsRestfulServiceImpl;
import com.ayoprez.castro.model.restful.RestfulService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import rx.Observable;
import rx.observers.TestSubscriber;

import static junit.framework.Assert.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 22.08.16.
 */
public class AboutUsRestfulServiceTest {
    private static final String TAG = AboutUsRestfulServiceTest.class.getSimpleName();

    private AboutUsRestfulService presenter;
    private ArrayList<AboutUs> aboutUs;

    @Mock
    CommonActivityView mockView;

    @Mock
    AboutUsRepository mockRepository;

    @Mock
    RestfulService mockRestfulService;

    @Mock
    Observable<ArrayList<AboutUs>> mockCall;


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);

        presenter = new AboutUsRestfulServiceImpl(mockRepository, mockRestfulService);
        aboutUs = new ArrayList<>(Collections.singletonList(new AboutUs()));
    }

    @Test
    public void shouldLoadAboutUs() throws Exception {
        when(mockRestfulService.getAboutUsFromServer()).thenReturn(mockCall.toSingle());

        TestSubscriber<ArrayList<AboutUs>> testSubscriber = new TestSubscriber<>();
        presenter.getRestfulAboutUs(mockView);
        mockRestfulService.getAboutUsFromServer().subscribe(testSubscriber);
        testSubscriber.assertNoErrors();
        testSubscriber.assertReceivedOnNext(Arrays.asList(aboutUs, aboutUs));
        verify(mockRepository, times(1)).saveAboutUs((AboutUs) anyObject());
    }

//    @Test
//    public void shouldSuccess() throws Exception{
//        when(mockRestfulService.getAboutUsFromServer()).thenReturn(mockCall);
//        Response<ArrayList<AboutUs>> res = Response.success(aboutUs);
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulAboutUs(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, never()).showErrorMessage(anyByte());
//        verify(mockRepository, times(1)).saveAboutUs(res.body().get(0));
//    }
//
//    @Test
//    public void shouldFailInSuccess() throws Exception{
//        when(mockRestfulService.getAboutUsFromServer()).thenReturn(mockCall);
//        Response<ArrayList<AboutUs>> res = Response.error(400, ResponseBody.create(MediaType.parse("applicacion/json"), "Body is null"));
//        when(mockCall.execute()).thenReturn(res);
//        presenter.getRestfulAboutUs(mockView);
//
//        verify(mockCall, times(1)).execute();
//
//        verify(mockView, times(1)).showErrorMessage(anyByte());
//        verify(mockRepository, never()).saveAboutUs((AboutUs) anyObject());
//    }

    @Test
    public void shouldNotDeleteAllIfThereIsNothing(){
        when(mockRepository.getAboutUs()).thenReturn(null);

        verify(mockRepository, never()).deleteAboutUs();
    }

    @Test
    public void shouldSaveDataInDatabase(){

    }

    @Test
    public void shouldDeleteAllWhenThereIsSomething(){
        ArrayList<AboutUs> aboutUsItems = new ArrayList<>();
        AboutUs aboutUsItem = new AboutUs();
        AboutUsMeta aboutUsItemMeta = new AboutUsMeta();
        aboutUsItemMeta.setDescription("");
        aboutUsItem.setTitle("");
        aboutUsItem.setMeta(aboutUsItemMeta);
        aboutUsItems.add(aboutUsItem);

        when(mockRepository.getAboutUs()).thenReturn(aboutUsItem);

        presenter = new AboutUsRestfulServiceImpl(mockRepository, mockRestfulService);

        verify(mockRepository, times(1)).deleteAboutUs();
    }
}
