package com.ayoprez.castroapp.ui.fragments.players;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.players.PlayersPresenter;
import com.ayoprez.castroapp.ui.adapters.EventsListAdapter;
import com.ayoprez.castroapp.ui.adapters.PlayersListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersFragment extends Fragment implements PlayersView{

    @Inject
    PlayersPresenter playersPresenter;

    protected PlayersListAdapter adapter;

    @BindView(R.id.eventList)
    protected RecyclerView recyclerView;

    public PlayersFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
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
    }

    @Override
    public void showEmptyListMessage() {
        //TODO
    }

    @Override
    public void initRecyclerView() {
        adapter = new PlayersListAdapter();
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }
}
