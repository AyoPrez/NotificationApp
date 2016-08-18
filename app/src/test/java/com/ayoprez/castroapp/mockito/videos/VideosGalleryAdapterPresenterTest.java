package com.ayoprez.castroapp.mockito.videos;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.VideoItem;
import com.ayoprez.castroapp.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.videos.VideoGalleryAdapterPresenterImpl;
import com.ayoprez.castroapp.ui.viewholders.videos.VideoItemView;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
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

        item = initImageItem(1, "www.vid.com/1", "preview");

        itemsList = new ArrayList<>();
        itemsList.add(initImageItem(1, "www.vid.com/1", "preview"));
        itemsList.add(initImageItem(2, "www.vid.com/2", "preview"));
        itemsList.add(initImageItem(3, "www.vid.com/3", "preview"));

        when(mockRepository.getVideo(anyInt())).thenReturn(item);

        when(mockRepository.getAllVideos()).thenReturn(itemsList);

        mockView = mock(VideoItemView.class);
        presenter = new VideoGalleryAdapterPresenterImpl(mockRepository);
    }

    public VideoItem initImageItem(int id, String video, String preview){
        VideoItem imageItem = new VideoItem();
        imageItem.setId(id);
        imageItem.setVideoUrl(video);
        imageItem.setPreviewImageUrl(preview);
        return imageItem;
    }

    @Test
    public void shouldBeAbleToLoadTheImageFromRepositoryWhenValidImageIsPresent(){
        when(mockView.getItemPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());
        verify(mockRepository, never()).getAllVideos();

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, times(1)).displayItemPreview("preview");
        verify(mockView, never()).showError();
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenImageIsNotPresenter(){
        when(mockView.getItemPosition()).thenReturn(1);

        when(mockRepository.getVideo(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemPreview("preview");
        verify(mockView, times(1)).showError();
    }

    @Test
    public void shouldShowErrorMessageWhenImageDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.setVideoUrl("");
        item.setPreviewImageUrl("");

        when(mockRepository.getVideo(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getVideo(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemPreview("preview");
        verify(mockView, times(1)).showError();
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
