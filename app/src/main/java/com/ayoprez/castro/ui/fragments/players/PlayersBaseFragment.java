package com.ayoprez.castro.ui.fragments.players;

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
 * Created by ayo on 24.08.16.
 */
public class PlayersBaseFragment extends Fragment {

    @BindView(R.id.tabs_categories)
    TabLayout tabLayout;

    @BindView(R.id.viewpager_categories)
    ViewPager viewPager;

    public static PlayersBaseFragment instance;

    public static PlayersBaseFragment getInstance() {
        if (instance == null){
            instance = new PlayersBaseFragment();
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
        View v = inflater.inflate(R.layout.fragment_playersinfo, container, false);
        ButterKnife.bind(this, v);

        viewPager.setOffscreenPageLimit(3);
        setupViewPager(viewPager);

        tabLayout.setupWithViewPager(viewPager);

        return v;
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager());
        adapter.addFragment(new PlayersSeniorFragment(), getString(R.string.senior));
        adapter.addFragment(new PlayersJuniorFragment(), getString(R.string.junior));
        adapter.addFragment(new PlayersCadetFragment(), getString(R.string.cadet));
        viewPager.setAdapter(adapter);
    }
}
