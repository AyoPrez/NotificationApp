package com.ayoprez.castroapp.ui.fragments.videos;

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
import com.ayoprez.castroapp.presenter.videos.VideosGalleryPresenter;
import com.ayoprez.castroapp.ui.adapters.VideosGalleryListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryFragment extends Fragment implements VideosGalleryView {
    private static final String TAG = VideosGalleryFragment.class.getSimpleName();

    @BindView(R.id.eventList)
    protected RecyclerView recyclerView;

    VideosGalleryListAdapter adapter;

    @Inject
    VideosGalleryPresenter presenter;

    public VideosGalleryFragment(){ }

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
        presenter.setView(this);
    }

    @Override
    public void showEmptyListMessage() {

    }

    @Override
    public void initRecyclerView() {
        adapter = new VideosGalleryListAdapter(getActivity());
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}