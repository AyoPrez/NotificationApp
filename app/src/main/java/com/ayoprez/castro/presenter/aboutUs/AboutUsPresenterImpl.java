package com.ayoprez.castro.presenter.aboutUs;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.AboutUs;
import com.ayoprez.castro.repository.AboutUsRepository;
import com.ayoprez.castro.ui.fragments.aboutus.AboutUsView;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsPresenterImpl extends ErrorManager implements AboutUsPresenter {
    private static final String TAG = AboutUsPresenterImpl.class.getSimpleName();

    private AboutUsView view;
    private AboutUsRepository repository;
    private AboutUs aboutUs;

    public AboutUsPresenterImpl(AboutUsRepository aboutUsRepository){
        this.repository = aboutUsRepository;
    }

    @Override
    public void loadAboutUsData() {
        aboutUs = repository.getAboutUs();

        if(aboutUs == null){
            showError(view, ERROR_EMPTY_ABOUTUS);
        }else{
            view.displayName(aboutUs.getTitle());
            view.displayDescription(aboutUs.getMeta().getDescription());
            view.displayImage(aboutUs.getMeta().getShare_image());
        }
    }

    @Override
    public void setView(AboutUsView view) {
        if(view == null){
            throw new ViewNotFoundException();
        }
        this.view = view;
        loadAboutUsData();
    }

    @Override
    public void openShare() {
        String shareText = aboutUs.getMeta().getShare_text();
        if(shareText == null || shareText.trim().equals("")){
            showError(view, ERROR_NO_DATA_ABOUTUS);
        }else{
            view.openShare(shareText);
        }
    }

    @Override
    public void openCall() {
        String number = aboutUs.getMeta().getNumber();
        if(number == null || number.trim().equals("")){
            showError(view, ERROR_NO_DATA_ABOUTUS);
        }else{
            view.openPhone(number);
        }
    }

    @Override
    public void openMail() {
        String mail = aboutUs.getMeta().getEmail();
        if(mail == null || mail.trim().equals("")){
            showError(view, ERROR_NO_DATA_ABOUTUS);
        }else{
            view.openMail(mail);
        }
    }

}
