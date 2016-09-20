package com.ayoprez.castro.ui.fragments.games;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.games.GamesCalendarPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ayo on 16.08.16.
 */
public class GamesCalendarFragment extends Fragment implements GamesCalendarView{

    @Inject
    GamesCalendarPresenter presenter;

    @Inject
    ErrorNotification errorNotification;

    @Inject
    ImageLib imageLib;

    protected PhotoViewAttacher photoZoom;

    @BindView(R.id.iv_season_calendar)
    ImageView iVCalendar;

    private static GamesCalendarFragment instance;

    public static GamesCalendarFragment getInstance() {
        if(instance == null) {
            instance = new GamesCalendarFragment();
        }

        return instance;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
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
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void displayCalendar(String url) {
        imageLib.setImageIntoView(url, iVCalendar);
        photoZoom = new PhotoViewAttacher(iVCalendar);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        errorNotification.showNotification(getView(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }
}
