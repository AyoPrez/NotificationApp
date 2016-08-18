package com.ayoprez.castroapp.mockito.videos;

import android.provider.MediaStore;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.VideoItem;
import com.ayoprez.castroapp.presenter.videos.VideosGalleryPresenter;
import com.ayoprez.castroapp.presenter.videos.VideosGalleryPresenterImpl;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryPresenterTest {

    VideosGalleryView mockView;
    VideosGalleryPresenter presenter;
    VideoItem item;
    VideosGalleryRepository repository;

    @Before
    public void setup(){
        item = new VideoItem();

        repository = mock(VideosGalleryRepository.class);

        item.setId(1);
        item.setVideoUrl("PlayerName");

        mockView = mock(VideosGalleryView.class);

        presenter = new VideosGalleryPresenterImpl(repository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);
    }

    @Test
    public void shouldShowEmptyListMessageWhenNoImagesInRepository(){
        ArrayList<VideoItem> items = new ArrayList<>();
        when(repository.getAllVideos()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereAreImagesInRepository(){
        ArrayList<VideoItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllVideos()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
    }

}
