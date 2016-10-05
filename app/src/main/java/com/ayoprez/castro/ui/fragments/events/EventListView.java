package com.ayoprez.castro.ui.fragments.events;

import android.support.v4.app.Fragment;

import com.ayoprez.castro.common.CommonListView;
import com.ayoprez.castro.models.EventItem;

/**
 * Created by ayo on 10.07.16.
 */
public interface EventListView extends CommonListView {

    void changeFragment(Fragment fragment, int id);
}
