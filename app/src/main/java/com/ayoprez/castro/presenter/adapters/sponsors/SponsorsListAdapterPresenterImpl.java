package com.ayoprez.castro.presenter.adapters.sponsors;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.SponsorItem;
import com.ayoprez.castro.repository.SponsorRepository;
import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsListAdapterPresenterImpl extends ErrorManager implements SponsorListAdapterPresenter {
    private static final String TAG = SponsorsListAdapterPresenterImpl.class.getSimpleName();

    protected SponsorRepository repository;
    protected SponsorItemView view;
    protected SponsorItem item;

    public SponsorsListAdapterPresenterImpl(SponsorRepository repository){
        this.repository = repository;
    }

    @Override
    public void setView(SponsorItemView view) {
        this.view = view;
        loadSponsors();
    }

    @Override
    public void loadSponsors() {
        if(view == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = view.getItemPosition();
        item = repository.getSponsor(itemPosition);

        if(item == null){
            showError(view, ERROR_EMPTY_SPONSORS, itemPosition);
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(SponsorItem item){
        if(item == null || item.getMeta() == null || item.getMeta().getPhoto() == null || item.getMeta().getPhoto().equals("")){
            showError(view, ERROR_NO_DATA_SPONSOR, view.getItemPosition());
        }else {
            view.displayItemImage(item.getMeta().getPhoto());
        }
    }

    @Override
    public int getSponsorsCountSize() {
        return repository.getAllSponsors().size();
    }
}
