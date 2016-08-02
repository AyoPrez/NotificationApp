package com.ayoprez.castroapp.presenter.adapters.sponsors;

import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemView;
import com.ayoprez.castroapp.ui.viewholders.sponsor.SponsorItemView;

/**
 * Created by ayo on 17.07.16.
 */
public interface SponsorListAdapterPresenter {

    void setView(SponsorItemView view);
    void loadSponsors();

    int getSponsorsCountSize();
}
