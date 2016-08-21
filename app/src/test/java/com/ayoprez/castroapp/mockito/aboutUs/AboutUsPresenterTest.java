package com.ayoprez.castroapp.mockito.aboutUs;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.presenter.aboutUs.AboutUsPresenter;
import com.ayoprez.castroapp.presenter.aboutUs.AboutUsPresenterImpl;
import com.ayoprez.castroapp.repository.AboutUsRepository;
import com.ayoprez.castroapp.ui.fragments.aboutus.AboutUsView;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsPresenterTest {
    private static final String TAG = AboutUsPresenterTest.class.getSimpleName();

    AboutUsRepository mockRepository;
    AboutUsView mockView;
    AboutUsPresenter presenter;
    AboutUs aboutUs;

    @Before
    public void setup(){
        mockRepository = mock(AboutUsRepository.class);
        aboutUs = new AboutUs();

        aboutUs.setTitle("Castro Morales");
        aboutUs.getMeta().setDescription("Sumergete en la descripción");
        aboutUs.getMeta().setShare_image("www.url.es/mierda");
        aboutUs.getMeta().setNumber("123456789");
        aboutUs.getMeta().setShare_text("Shared text");
        aboutUs.getMeta().setEmail("mail@mail.com");
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);

        mockView = mock(AboutUsView.class);

        presenter = new AboutUsPresenterImpl(mockRepository);
    }

    @Test
    public void shouldBeAbleToLoadTheAboutUsDataFromTheRepositoryWhenIsValid() {
        presenter.setView(mockView);

        verify(mockRepository, times(1)).getAboutUs();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).showErrorMessage();
        verify(mockView, never()).clickCallButton();
        verify(mockView, never()).clickShareButton();
        verify(mockView, never()).clickMailButton();
        verify(mockView, never()).openPhone(aboutUs.getMeta().getNumber());
        verify(mockView, never()).openMail(aboutUs.getMeta().getEmail());
        verify(mockView, never()).openShare(aboutUs.getMeta().getShare_text());
    }

    @Test
    public void shouldShowErrorMessageOnViewWhenThereIsNoData() {

        when(mockRepository.getAboutUs()).thenReturn(null);

        presenter.setView(mockView);

        verify(mockRepository, times(1)).getAboutUs();

        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).displayName("Castro Morales");
        verify(mockView, never()).displayDescription("Sumergete en la descripción");
        verify(mockView, never()).displayImage("www.url.es/mierda");
        verify(mockView, times(1)).showErrorMessage();
        verify(mockView, never()).clickCallButton();
        verify(mockView, never()).clickShareButton();
        verify(mockView, never()).clickMailButton();
        verify(mockView, never()).openPhone(aboutUs.getMeta().getNumber());
        verify(mockView, never()).openMail(aboutUs.getMeta().getEmail());
        verify(mockView, never()).openShare(aboutUs.getMeta().getShare_text());
    }

    @Test
    public void shouldOpenPhone() {
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openCall();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, times(1)).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldOpenMail() {
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openMail();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, times(1)).openMail(anyString());
        verify(mockView, never()).showErrorMessage();
    }

    @Test
    public void shouldOpenShare() {
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openShare();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, times(1)).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, never()).showErrorMessage();
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
        presenter.loadAboutUsData();
    }

}
