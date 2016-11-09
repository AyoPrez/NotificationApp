package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.repository.SponsorRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 20.08.16.
 */
public class SponsorsRestfulServiceImpl extends ErrorManager implements SponsorsRestfulService {
    private static final String TAG = SponsorsRestfulServiceImpl.class.getSimpleName();

    private SponsorRepository repository;
    private RestfulService service;
    private Subscription subscription;


    public SponsorsRestfulServiceImpl(SponsorRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulSponsors(final CommonActivityView view) {

        subscription = service.getSponsorsFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<SponsorItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompleteSponsorsData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_SPONSORS);
                    }

                    @Override
                    public void onNext(ArrayList<SponsorItem> sponsor) {
                        repository.saveSponsor(sponsor);
                    }
                });
    }

    @Override
    public void deleteCompleteSponsorsData() {
        if(repository.getAllSponsors().size() > 0){
            repository.deleteAllSponsors();
        }
    }
}
