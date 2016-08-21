package com.ayoprez.castroapp.mockito.sponsor;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.SponsorItem;
import com.ayoprez.castroapp.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castroapp.presenter.sponsors.SponsorsPresenterImpl;
import com.ayoprez.castroapp.repository.SponsorRepository;
import com.ayoprez.castroapp.ui.fragments.SponsorsView;

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
public class SponsorsPresenterTest {

    SponsorsView mockView;
    SponsorsPresenter presenter;
    SponsorItem item;
    SponsorRepository repository;

    @Before
    public void setup(){
        item = new SponsorItem();

        repository = mock(SponsorRepository.class);

        item.setId(1);
        item.getMeta().setPhoto("http://www.images.com/image1");
        item.getMeta().setUrl("http://www.web.sponsor.com/");

        mockView = mock(SponsorsView.class);

        presenter = new SponsorsPresenterImpl(repository);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);
    }

    @Test
    public void shouldShowEmptyListMessageWhenNoImagesInRepository(){
        ArrayList<SponsorItem> items = new ArrayList<>();
        when(repository.getAllSponsors()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, times(1)).showEmptyListMessage();
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereAreImagesInRepository(){
        ArrayList<SponsorItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllSponsors()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage();
        verify(mockView, times(1)).initRecyclerView();
    }
}
