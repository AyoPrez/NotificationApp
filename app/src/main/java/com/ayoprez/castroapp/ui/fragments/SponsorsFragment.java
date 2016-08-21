package com.ayoprez.castroapp.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castroapp.ui.adapters.ImagesGalleryListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsFragment extends Fragment implements SponsorsView {

    @Inject
    SponsorsPresenter sponsorsPresenter;

    protected ImagesGalleryListAdapter adapter;

    @BindView(R.id.eventList)
    protected RecyclerView recyclerView;

    public SponsorsFragment(){ }

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
        sponsorsPresenter.setView(this);
    }

    @Override
    public void showEmptyListMessage() {

    }

    @Override
    public void initRecyclerView() {
        adapter = new ImagesGalleryListAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }
}
