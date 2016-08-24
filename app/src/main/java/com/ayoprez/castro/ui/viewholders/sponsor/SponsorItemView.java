package com.ayoprez.castro.ui.viewholders.sponsor;

import com.ayoprez.castro.common.CommonListItemView;

/**
 * Created by ayo on 17.07.16.
 */
public interface SponsorItemView extends CommonListItemView {
    int getItemPosition();

    void displayItemImage(String image);
}
