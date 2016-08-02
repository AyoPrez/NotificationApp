package com.ayoprez.castroapp.ui.fragments.events;

import android.support.v4.app.Fragment;

import com.ayoprez.castroapp.common.CommonListView;
import com.ayoprez.castroapp.models.EventItem;

import java.util.ArrayList;

/**
 * Created by ayo on 10.07.16.
 */
public interface EventView extends CommonListView{

    void changeFragment(Fragment fragment);
}
