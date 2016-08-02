package com.ayoprez.castroapp.presenter.sponsors;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.repository.SponsorRepository;
import com.ayoprez.castroapp.ui.fragments.SponsorsView;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsPresenterImpl implements SponsorsPresenter {

    private SponsorRepository sponsorRepository;
    private SponsorsView view;

    public SponsorsPresenterImpl(SponsorRepository repository){
        this.sponsorRepository = repository;
    }

    @Override
    public void setView(SponsorsView view) {
        this.view = view;
        initView();
    }

    private void initView(){
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(sponsorRepository.getAllSponsors().size() <= 0){
            view.showEmptyListMessage();
        }else {
            view.initRecyclerView();
        }
    }
}
