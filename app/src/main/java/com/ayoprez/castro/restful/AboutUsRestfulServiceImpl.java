package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.AboutUs;
import com.ayoprez.castro.repository.AboutUsRepository;

import java.util.ArrayList;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 19.08.16.
 */
public class AboutUsRestfulServiceImpl extends ErrorManager implements AboutUsRestfulService {
    private static final String TAG = AboutUsRestfulServiceImpl.class.getSimpleName();

    private AboutUsRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public AboutUsRestfulServiceImpl(AboutUsRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulAboutUs(final CommonActivityView view) {

        subscription = service.getAboutUsFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<AboutUs>>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onStart() {
                deleteCompleteAboutUsData();
            }

            @Override
            public void onError(Throwable e) {
                showError(view, ERROR_RESTFUL_ABOUTUS);
            }

            @Override
            public void onNext(ArrayList<AboutUs> aboutUses) {
                repository.saveAboutUs(aboutUses.get(0));
            }
        });
    }

    @Override
    public void deleteCompleteAboutUsData() {
        if(repository.getAboutUs() != null){
            repository.deleteAboutUs();
        }
    }
}
