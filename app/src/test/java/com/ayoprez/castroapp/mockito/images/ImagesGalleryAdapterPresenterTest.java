package com.ayoprez.castroapp.mockito.images;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenterImpl;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;
import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemView;

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
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryAdapterPresenterTest {

    ImagesGalleryRepository mockRepository;
    GalleryItemView mockView;
    GalleryAdapterPresenter presenter;
    ImageItem item;
    ArrayList<String> itemsList;

    @Before
    public void setUp(){
        mockRepository = mock(ImagesGalleryRepository.class);

        item = initImageItem(1, "www.img.com/1");

        itemsList = new ArrayList<>();
        itemsList.add(initImageItem(1, "www.img.com/1").getMeta().getPhoto());
        itemsList.add(initImageItem(2, "www.img.com/2").getMeta().getPhoto());
        itemsList.add(initImageItem(3, "www.img.com/3").getMeta().getPhoto());

        when(mockRepository.getImage(anyInt())).thenReturn(item.getMeta().getPhoto());

        when(mockRepository.getAllImages()).thenReturn(itemsList);

        mockView = mock(GalleryItemView.class);
        presenter = new GalleryAdapterPresenterImpl(mockRepository);
    }

    public ImageItem initImageItem(int id, String image){
        ImageItem imageItem = new ImageItem();
        imageItem.setId(id);
        imageItem.getMeta().setPhoto(image);
        return imageItem;
    }

    @Test
    public void shouldBeAbleToLoadTheImageFromRepositoryWhenValidImageIsPresent(){
        when(mockView.getItemPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getImage(anyInt());
        verify(mockRepository, never()).getAllImages();

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, times(1)).displayItemImage("www.img.com/1");
        verify(mockView, never()).showError();
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenImageIsNotPresenter(){
        when(mockView.getItemPosition()).thenReturn(1);

        when(mockRepository.getImage(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getImage(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).showError();
    }

    @Test
    public void shouldShowErrorMessageWhenImageDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.getMeta().setPhoto("");

        when(mockRepository.getImage(anyInt())).thenReturn(item.getMeta().getPhoto());

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getImage(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).showError();
    }

    @Test
    public void shouldGetTotalNumberOfImages(){
        presenter.setView(mockView);

        assertEquals(presenter.getImagesCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadImages();
    }

}
