package com.ayoprez.castroapp.mockito.images;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.ImageItem;
import com.ayoprez.castroapp.presenter.images.GalleryPresenter;
import com.ayoprez.castroapp.presenter.images.GalleryPresenterImpl;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;
import com.ayoprez.castroapp.ui.fragments.images.ImagesGalleryView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryPresenterTest {

    ImagesGalleryView mockView;
    GalleryPresenter presenter;
    ImageItem item;
    ImagesGalleryRepository repository;

    @Before
    public void setup(){
        item = new ImageItem();

        repository = mock(ImagesGalleryRepository.class);

        item.setId(1);
        item.setImage("PlayerName");

        mockView = mock(ImagesGalleryView.class);

        presenter = new GalleryPresenterImpl(repository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);
    }

    @Test
    public void shouldShowEmptyListMessageWhenNoImagesInRepository(){
        ArrayList<String> items = new ArrayList<>();
        when(repository.getAllImages()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereAreImagesInRepository(){
        ArrayList<String> items = new ArrayList<>();
        items.add(item.getImage());
        when(repository.getAllImages()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
    }
}
