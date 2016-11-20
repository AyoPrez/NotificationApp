package com.ayoprez.castro.ui.fragments.players;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.model.models.PlayerItem;
import com.ayoprez.castro.presenter.players.PlayersPresenter;
import com.ayoprez.castro.ui.adapters.PlayersListAdapter;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 24.08.16.
 */
public class PlayersCadetFragment extends Fragment implements PlayersView {

    @Inject
    PlayersPresenter playersPresenter;

    @Inject
    ErrorNotification errorNotification;

    protected PlayersListAdapter adapter;

    @BindView(R.id.recyclerViewList)
    protected RecyclerView recyclerView;

    public PlayersCadetFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initComponent();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recyclerview, container, false);
        ButterKnife.bind(this, v);
        return v;
    }

    @Override
    public void onResume() {
        super.onResume();
        playersPresenter.setView(this);
        playersPresenter.setCategoryTag("Cadet");
        playersPresenter.initView();
    }

    @Override
    public void initRecyclerView(ArrayList<PlayerItem> content) {
        adapter = new PlayersListAdapter(getActivity(), content);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    protected void initComponent(){
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        if (isAdded())
            errorNotification.showNotification(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }
}
