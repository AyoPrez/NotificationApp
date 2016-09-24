package com.ayoprez.castro.ui.fragments.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.R;
import com.ayoprez.castro.ui.adapters.ViewPagerAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 10.07.16.
 */
public class GamesFragment extends Fragment {

    @BindView(R.id.tabs_season)
    TabLayout tabLayout;

    @BindView(R.id.viewpager_season)
    ViewPager viewPager;

    public static GamesFragment instance;

    public static GamesFragment getInstance() {
        if (instance == null){
            instance = new GamesFragment();
        }

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_seasoninfo, container, false);
        ButterKnife.bind(this, v);

        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity().getSupportFragmentManager());
        adapter.addFragment(GamesCalendarFragment.getInstance(), getString(R.string.results));
        adapter.addFragment(GamesTableFragment.getInstance(), getString(R.string.table));
        viewPager.setAdapter(adapter);
    }

}
