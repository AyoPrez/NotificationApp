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
import com.ayoprez.castroapp.presenter.games.GamesTablePresenter;
import com.squareup.picasso.Picasso;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 16.08.16.
 */
public class GamesTableFragment extends Fragment implements GamesTableView{
    private static final String TAG = GamesTableFragment.class.getSimpleName();
    private Context context;

    @BindView(R.id.table_image)
    ImageView ivTable;

    @Inject
    GamesTablePresenter presenter;

    private static GamesTableFragment instance;

    public GamesTableFragment(){
        startComponent();
    }

    public static GamesTableFragment getInstance() {
        if(instance == null) {
            instance = new GamesTableFragment();
        }

        return instance;
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
        this.context = inflater.getContext();
        ButterKnife.bind(this, view);

        return view;
    }

    private void startComponent(){
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void showErrorMessage() {

    }

    @Override
    public void displayTable(String url) {
        Picasso.with(context).load(url).fit().into(ivTable);
    }
}
