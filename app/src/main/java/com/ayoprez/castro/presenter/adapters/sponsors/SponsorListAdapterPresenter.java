package com.ayoprez.castro.presenter.adapters.sponsors;

import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemView;

/**
 * Created by ayo on 17.07.16.
 */
public interface SponsorListAdapterPresenter {

    void setItemView(SponsorItemView view);
    void loadSponsors();

    int getSponsorsCountSize();

    void loadUrl(int position);
}
