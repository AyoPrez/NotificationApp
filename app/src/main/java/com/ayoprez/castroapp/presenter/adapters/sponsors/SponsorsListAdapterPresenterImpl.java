package com.ayoprez.castroapp.presenter.adapters.sponsors;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.SponsorItem;
import com.ayoprez.castroapp.repository.SponsorRepository;
import com.ayoprez.castroapp.ui.viewholders.sponsor.SponsorItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsListAdapterPresenterImpl implements SponsorListAdapterPresenter {
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
            view.showError();
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(SponsorItem item){
        if(item == null || item.getMeta() == null || item.getMeta().getPhoto() == null || item.getMeta().getPhoto().equals("")){
            view.showError();
        }else {
            view.displayItemImage(item.getMeta().getPhoto());
        }
    }

    @Override
    public int getSponsorsCountSize() {
        return repository.getAllSponsors().size();
    }
}
