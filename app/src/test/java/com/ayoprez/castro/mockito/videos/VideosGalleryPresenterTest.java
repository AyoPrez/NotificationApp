package com.ayoprez.castro.mockito.videos;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.model.models.VideoItem;
import com.ayoprez.castro.model.models.VideoItemMeta;
import com.ayoprez.castro.presenter.videos.VideosGalleryPresenter;
import com.ayoprez.castro.presenter.videos.VideosGalleryPresenterImpl;
import com.ayoprez.castro.model.repository.VideosGalleryRepository;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyByte;
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
    VideoItemMeta videoItemMeta;
    VideosGalleryRepository repository;

    @Before
    public void setup(){
        item = new VideoItem();
        videoItemMeta = new VideoItemMeta();

        repository = mock(VideosGalleryRepository.class);

        item.setId(1);
        item.setTitle("Video1");
        videoItemMeta.setVideo("VideoURL");
        item.setMeta(videoItemMeta);

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

        verify(mockView, times(1)).showEmptyListMessage(anyByte());
        try {
            verify(mockView, never()).initRecyclerView();
        }catch(Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }
    }

    @Test
    public void shouldShowRecyclerViewWhenThereAreImagesInRepository(){
        ArrayList<VideoItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllVideos()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage(anyByte());
        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch (Exception e){
            verify(mockView, times(1)).showEmptyListMessage(anyByte());
        }
    }

    @Test
    public void shouldGetRightPreviewURL(){
        VideoItemMeta videoItemMeta = new VideoItemMeta();
        videoItemMeta.setVideo("https://www.youtube.com/watch?v=mQ-MGu0tv5I");

        assertEquals(videoItemMeta.getPreview(), String.format("http://img.youtube.com/vi/%1$s/0.jpg", "mQ-MGu0tv5I"));
    }

}
