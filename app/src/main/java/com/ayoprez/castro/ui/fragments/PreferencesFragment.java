package com.ayoprez.castro.ui.fragments;

import android.os.Bundle;
import android.preference.PreferenceFragment;

import com.ayoprez.castro.R;

/**
 * Created by ayo on 04.12.16.
 */

public class PreferencesFragment extends PreferenceFragment {

    private static PreferencesFragment instance;

    public static PreferencesFragment getInstance() {
        if(instance == null) {
            instance = new PreferencesFragment();
        }

        return instance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addPreferencesFromResource(R.xml.fragment_preferences);
    }
}
