package com.ayoprez.castro.presenter.sponsors;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsView;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsPresenterImpl extends ErrorManager implements SponsorsPresenter {

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
            showError(view, ERROR_EMPTY_SPONSORS);
        }else {
            view.initRecyclerView();
        }
    }
}
