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
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.games.GamesTablePresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 16.08.16.
 */
public class GamesTableFragment extends Fragment implements GamesTableView{
    private static final String TAG = GamesTableFragment.class.getSimpleName();

    @Inject
    GamesTablePresenter presenter;

    @Inject
    ImageLib imageLib;

    @BindView(R.id.table_image)
    ImageView ivTable;

    private static GamesTableFragment instance;

    public GamesTableFragment() { }

    public static GamesTableFragment getInstance() {
        if(instance == null) {
            instance = new GamesTableFragment();
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
        View view = inflater.inflate(R.layout.fragment_season_table, container, false);
        ButterKnife.bind(this, view);

        return view;
    }

    @Override
    public void displayTable(String url) {
        imageLib.setImageIntoView(url, ivTable);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        Toast.makeText(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage], Toast.LENGTH_LONG).show();
    }
}
