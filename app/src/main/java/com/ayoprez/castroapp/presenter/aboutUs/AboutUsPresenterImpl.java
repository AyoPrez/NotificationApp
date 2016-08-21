package com.ayoprez.castroapp.presenter.aboutUs;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.AboutUs;
import com.ayoprez.castroapp.repository.AboutUsRepository;
import com.ayoprez.castroapp.ui.fragments.aboutus.AboutUsView;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsPresenterImpl implements AboutUsPresenter {
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
            view.showErrorMessage();
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
            view.showErrorMessage();
        }else{
            view.openShare(shareText);
        }
    }

    @Override
    public void openCall() {
        String number = aboutUs.getMeta().getNumber();
        if(number == null || number.trim().equals("")){
            view.showErrorMessage();
        }else{
            view.openPhone(number);
        }
    }

    @Override
    public void openMail() {
        String mail = aboutUs.getMeta().getEmail();
        if(mail == null || mail.trim().equals("")){
            view.showErrorMessage();
        }else{
            view.openMail(mail);
        }
    }

}
