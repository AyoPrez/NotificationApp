package com.ayoprez.castroapp.ui.fragments.games;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.games.GamesCalendarPresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 16.08.16.
 */
public class GamesCalendarFragment extends Fragment implements GamesCalendarView{

    private Context context;

    @BindView(R.id.iv_season_calendar)
    ImageView iVCalendar;

    @Inject
    GamesCalendarPresenter presenter;

    private static GamesCalendarFragment instance;

    public static GamesCalendarFragment getInstance() {
        if(instance == null) {
            instance = new GamesCalendarFragment();
        }

        return instance;
    }

    public GamesCalendarFragment(){
        startComponent();
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.setView(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_season_calendar, container, false);
        this.context = inflater.getContext();
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void displayCalendar(String url) {
        Picasso.with(context).load(url).fit().into(iVCalendar);
    }

    private void startComponent(){
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }
}
