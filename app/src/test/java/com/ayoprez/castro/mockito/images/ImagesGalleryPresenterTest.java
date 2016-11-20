package com.ayoprez.castro.mockito.images;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.model.models.ImageItem;
import com.ayoprez.castro.model.models.ImageItemMeta;
import com.ayoprez.castro.presenter.images.GalleryPresenter;
import com.ayoprez.castro.presenter.images.GalleryPresenterImpl;
import com.ayoprez.castro.model.repository.ImagesGalleryRepository;
import com.ayoprez.castro.ui.fragments.images.ImagesGalleryView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.anyByte;
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
    ImageItemMeta imageItemMeta;
    ImagesGalleryRepository repository;

    @Before
    public void setup(){
        item = new ImageItem();
        imageItemMeta = new ImageItemMeta();

        repository = mock(ImagesGalleryRepository.class);

        item.setId(1);
        imageItemMeta.setPhoto("ImageName");
        item.setMeta(imageItemMeta);

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
        when(repository.getAllStringImages()).thenReturn(items);

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
        ArrayList<String> items = new ArrayList<>();
        items.add(item.getMeta().getPhoto());
        when(repository.getAllStringImages()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage(anyByte());
        try {
            verify(mockView, times(1)).initRecyclerView();
        }catch(Exception e){
                verify(mockView, times(1)).showEmptyListMessage(anyByte());
            }
    }
}
