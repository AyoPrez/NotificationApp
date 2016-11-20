package com.ayoprez.castro.presenter.sponsors;

import android.os.Bundle;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.model.models.SponsorItem;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castro.model.repository.SponsorRepository;
import com.ayoprez.castro.ui.fragments.WebViewFragment;
import com.ayoprez.castro.ui.fragments.sponsors.SponsorsView;
import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsPresenterImpl extends ErrorManager implements SponsorsPresenter, SponsorListAdapterPresenter {

    private SponsorRepository sponsorRepository;
    private static SponsorsView listView;
    protected SponsorItemView itemView;
    protected SponsorItem item;

    public SponsorsPresenterImpl(SponsorRepository repository){
        this.sponsorRepository = repository;
    }

    @Override
    public void setView(SponsorsView view) {
        if(listView == null) {
            listView = view;
        }
        initView();
    }

    private void initView(){
        if(listView == null){
            throw new ViewNotFoundException();
        }

        if(sponsorRepository.getAllSponsors().size() <= 0){
            showError(listView, ERROR_EMPTY_SPONSORS);
        }else {
            try {
                listView.initRecyclerView();
            }catch(Exception e){
                showError(listView, ERROR);
            }
        }
    }

    @Override
    public void setItemView(SponsorItemView view) {
        this.itemView = view;
        loadSponsors();
    }

    @Override
    public void loadSponsors() {
        if(itemView == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = itemView.getItemPosition();
        item = sponsorRepository.getSponsorItemByPosition(itemPosition);

        if(item == null){
            showError(itemView, ERROR_EMPTY_SPONSORS, itemPosition);
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(SponsorItem item){
        if(item == null || item.getMeta() == null || item.getMeta().getPhoto() == null || item.getMeta().getPhoto().equals("")){
            showError(itemView, ERROR_NO_DATA_SPONSOR, itemView.getItemPosition());
        }else {
            itemView.displayItemImage(item.getMeta().getPhoto());
        }
    }

    @Override
    public void loadUrl(int position) {
        SponsorItem item = sponsorRepository.getSponsorItemByPosition(position);

        if(item == null || item.getTitle() == null || item.getTitle().trim().equals("")) {
            showError(itemView, ERROR_EMPTY_SPONSORS, position);
        }else{
            WebViewFragment webViewFragment = new WebViewFragment();

            Bundle bundle = new Bundle();
            bundle.putString("url", item.getMeta().getUrl());

            webViewFragment.setArguments(bundle);

            if(listView != null) {
                listView.changeFragment(webViewFragment);
            }
        }
    }

    @Override
    public int getSponsorsCountSize() {
        return sponsorRepository.getAllSponsors().size();
    }
}
