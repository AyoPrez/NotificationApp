package com.ayoprez.castro.mockito.aboutUs;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.models.AboutUs;
import com.ayoprez.castro.models.AboutUsMeta;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenter;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenterImpl;
import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.ui.fragments.aboutus.AboutUsView;

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

    protected AboutUsRepository mockRepository;
    protected AboutUsView mockView;
    protected AboutUsPresenter presenter;
    protected AboutUs aboutUs;
    protected AboutUsMeta aboutUsMeta;

    @Before
    public void setup(){
        mockRepository = mock(AboutUsRepository.class);
        aboutUs = new AboutUs();
        aboutUsMeta = new AboutUsMeta();

        aboutUs.setId(1);
        aboutUs.setTitle("Castro Morales");
        aboutUsMeta.setDescription("Sumergete en la descripción");
        aboutUsMeta.setShare_image("www.url.es/mierda");
        aboutUsMeta.setShare_text("This texts is shared");
        aboutUsMeta.setNumber("123456789");
        aboutUsMeta.setShare_text("Shared text");
        aboutUsMeta.setEmail("mail@mail.com");
        aboutUs.setMeta(aboutUsMeta);

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
        verify(mockView, never()).showErrorMessage(anyString());
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

        verify(mockView, times(1)).showErrorMessage(anyString());
        verify(mockView, never()).displayName("Castro Morales");
        verify(mockView, never()).displayDescription("Sumergete en la descripción");
        verify(mockView, never()).displayImage("www.url.es/mierda");
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
        verify(mockView, never()).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenPhoneButNumberIsNull(){
        aboutUsMeta.setNumber(null);
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openCall();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenPhoneButNumberIsEmpty(){
        aboutUsMeta.setNumber("");
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openCall();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
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
        verify(mockView, never()).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenMailButMailIsNull(){
        aboutUsMeta.setEmail(null);
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openMail();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenMailButMailIsEmpty(){
        aboutUsMeta.setEmail("");
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openMail();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
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
        verify(mockView, never()).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenShareButDataAreNull(){
        aboutUsMeta.setShare_text(null);
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openShare();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
    }

    @Test
    public void shouldOpenShareButDataAreEmpty(){
        aboutUsMeta.setShare_text(null);
        aboutUs.setMeta(aboutUsMeta);
        when(mockRepository.getAboutUs()).thenReturn(aboutUs);
        presenter.setView(mockView);
        verify(mockRepository, times(1)).getAboutUs();

        presenter.openShare();

        verify(mockView, times(1)).displayName("Castro Morales");
        verify(mockView, times(1)).displayDescription("Sumergete en la descripción");
        verify(mockView, times(1)).displayImage("www.url.es/mierda");
        verify(mockView, never()).openShare(anyString());
        verify(mockView, never()).openPhone(anyString());
        verify(mockView, never()).openMail(anyString());
        verify(mockView, times(1)).showErrorMessage(anyString());
    }

    @Test(expected = ViewNotFoundException.class)
    public void shouldThrowViewNotFoundExceptionWhenViewIsNull() {
        presenter.setView(null);
        presenter.loadAboutUsData();
    }

}
