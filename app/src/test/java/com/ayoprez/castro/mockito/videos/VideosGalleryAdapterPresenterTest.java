package com.ayoprez.castro.mockito.videos;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.models.VideoItemMeta;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenterImpl;
import com.ayoprez.castro.ui.viewholders.videos.VideoItemView;
import com.ayoprez.castro.repository.VideosGalleryRepository;

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
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryAdapterPresenterTest  {
    private static final String TAG = VideosGalleryAdapterPresenterTest.class.getSimpleName();

    VideosGalleryRepository mockRepository;
    VideoItemView mockView;
    VideoGalleryAdapterPresenter presenter;
    VideoItem item;
    ArrayList<VideoItem> itemsList;

    @Before
    public void setUp(){
        mockRepository = mock(VideosGalleryRepository.class);

        item = initVideoItem(1, "Video1", "www.vid.com/1", "preview");

        itemsList = new ArrayList<>();
        itemsList.add(initVideoItem(1, "Video1", "www.vid.com/1", "preview"));
        itemsList.add(initVideoItem(2, "Video2", "www.vid.com/2", "preview"));
        itemsList.add(initVideoItem(3, "Video3", "www.vid.com/3", "preview"));

        when(mockRepository.getVideo(anyInt())).thenReturn(item);

        when(mockRepository.getAllVideos()).thenReturn(itemsList);

        mockView = mock(VideoItemView.class);
        presenter = new VideoGalleryAdapterPresenterImpl(mockRepository);
    }

    public VideoItem initVideoItem(int id, String title, String video, String preview){
        VideoItem videoItem = new VideoItem();
        VideoItemMeta videoItemMeta = new VideoItemMeta();
        videoItem.setId(id);
        videoItem.setTitle(title);
        videoItemMeta.setVideo(video);
        videoItemMeta.setPreview(preview);
        videoItem.setMeta(videoItemMeta);
        return videoItem;
    }

    @Test
    public void shouldBeAbleToLoadTheImageFromRepositoryWhenValidImageIsPresent(){
        when(mockView.getItemPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());
        verify(mockRepository, never()).getAllVideos();

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, times(1)).displayItemPreview("preview");
        verify(mockView, never()).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenImageIsNotPresenter(){
        when(mockView.getItemPosition()).thenReturn(1);

        when(mockRepository.getVideo(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemPreview("preview");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenImageDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.getMeta().setVideo("");
        item.getMeta().setPreview("");

        when(mockRepository.getVideo(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());

        verify(mockView, times(2)).getItemPosition();
        verify(mockView, never()).displayItemPreview("preview");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldGetTotalNumberOfImages(){
        presenter.setView(mockView);

        assertEquals(presenter.getVideosCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadVideos();
    }
}
