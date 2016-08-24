package com.ayoprez.castro.mockito.sponsor;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.models.SponsorItemMeta;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorsListAdapterPresenterImpl;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemView;

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
 * Created by ayo on 17.07.16.
 */
public class SponsorsAdapterPresenterTest {

    SponsorRepository mockRepository;
    SponsorItemView mockView;
    SponsorListAdapterPresenter presenter;
    SponsorItem item;
    ArrayList<SponsorItem> itemsList;

    @Before
    public void setUp(){
        mockRepository = mock(SponsorRepository.class);

        item = initSponsorItem(1, "Sponsor1", "www.img.com/1", "www.sponsor1.com");

        itemsList = new ArrayList<>();
        itemsList.add(initSponsorItem(1, "Sponsor1", "www.img.com/1", "www.sponsor1.com"));
        itemsList.add(initSponsorItem(2, "Sponsor1", "www.img.com/2", "www.sponsor2.com"));
        itemsList.add(initSponsorItem(3, "Sponsor1", "www.img.com/3", "www.sponsor3.com"));

        when(mockRepository.getSponsor(anyInt())).thenReturn(item);

        when(mockRepository.getAllSponsors()).thenReturn(itemsList);

        mockView = mock(SponsorItemView.class);
        presenter = new SponsorsListAdapterPresenterImpl(mockRepository);
    }

    public SponsorItem initSponsorItem(int id,String title, String image, String url){
        SponsorItem sponsorItem = new SponsorItem();
        SponsorItemMeta sponsorItemMeta = new SponsorItemMeta();
        sponsorItem.setId(id);
        sponsorItem.setTitle(title);
        sponsorItemMeta.setPhoto(image);
        sponsorItemMeta.setUrl(url);
        sponsorItem.setMeta(sponsorItemMeta);
        return sponsorItem;
    }

    @Test
    public void shouldBeAbleToLoadTheImageFromRepositoryWhenValidImageIsPresent(){
        when(mockView.getItemPosition()).thenReturn(1);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getSponsor(anyInt());
        verify(mockRepository, never()).getAllSponsors();

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, times(1)).displayItemImage("www.img.com/1");
        verify(mockView, never()).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageOnItemViewWhenImageIsNotPresenter(){
        when(mockView.getItemPosition()).thenReturn(1);

        when(mockRepository.getSponsor(anyInt())).thenReturn(null);
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getSponsor(anyInt());

        verify(mockView, times(1)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldShowErrorMessageWhenImageDataAreEmpty(){
        when(mockView.getItemPosition()).thenReturn(1);

        item.getMeta().setPhoto("");

        when(mockRepository.getSponsor(anyInt())).thenReturn(item);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getSponsor(anyInt());

        verify(mockView, times(2)).getItemPosition();
        verify(mockView, never()).displayItemImage("www.img.com/1");
        verify(mockView, times(1)).showErrorMessage(anyString(), anyInt());
    }

    @Test
    public void shouldGetTotalNumberOfImages(){
        presenter.setView(mockView);

        assertEquals(presenter.getSponsorsCountSize(), 3);
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull(){
        presenter.setView(null);

        presenter.loadSponsors();
    }
}
