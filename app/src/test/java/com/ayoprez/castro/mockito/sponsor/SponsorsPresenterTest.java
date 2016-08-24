package com.ayoprez.castro.mockito.sponsor;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.models.SponsorItemMeta;
import com.ayoprez.castro.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castro.presenter.sponsors.SponsorsPresenterImpl;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsView;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
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
    SponsorItemMeta sponsorItemMeta;
    SponsorRepository repository;

    @Before
    public void setup(){
        item = new SponsorItem();
        sponsorItemMeta = new SponsorItemMeta();

        repository = mock(SponsorRepository.class);

        item.setId(1);
        item.setTitle("Sponsor2");
        sponsorItemMeta.setPhoto("http://www.images.com/image1");
        sponsorItemMeta.setUrl("http://www.web.sponsor.com/");
        item.setMeta(sponsorItemMeta);

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

        verify(mockView, times(1)).showEmptyListMessage(anyString());
        verify(mockView, never()).initRecyclerView();
    }

    @Test
    public void shouldShowRecyclerViewWhenThereAreImagesInRepository(){
        ArrayList<SponsorItem> items = new ArrayList<>();
        items.add(item);
        when(repository.getAllSponsors()).thenReturn(items);

        presenter.setView(mockView);

        verify(mockView, never()).showEmptyListMessage(anyString());
        verify(mockView, times(1)).initRecyclerView();
    }
}
