package com.ayoprez.castro.ui.viewholders.videos;

import com.ayoprez.castro.common.CommonListItemView;

/**
 * Created by ayo on 18.08.16.
 */
public interface VideoItemView extends CommonListItemView {

    int getItemPosition();
    void displayItemPreview(String image);

}
