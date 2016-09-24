package com.ayoprez.castro.ui.fragments.events;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.presenter.events.EventPresenter;
import com.ayoprez.castro.ui.adapters.EventsListAdapter;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 26.06.16.
 */
public class EventListFragment extends Fragment implements EventListView {

    @Inject
    EventPresenter eventPresenter;

    @Inject
    ErrorNotification errorNotification;

    protected EventsListAdapter adapter;

    @BindView(R.id.recyclerViewList)
    protected RecyclerView recyclerView;

    public static EventListFragment instance;

    public static EventListFragment getInstance() {
        if (instance == null){
            instance = new EventListFragment();
        }

        return instance;
    }

    public EventListFragment(){}

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
        eventPresenter.setView(this);
    }

    @Override
    public void changeFragment(Fragment fragment, short id) {

        Bundle bundle = new Bundle();
        bundle.putShort("eventId", id);
        fragment.setArguments(bundle);

        try {
            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, fragment)
                    .addToBackStack("events")
                    .commit();
        }catch(Exception e){
            showEmptyListMessage(ErrorManager.ERROR);
        }
    }

    @Override
    public void showEmptyListMessage(byte errorMessage) {
        if (isAdded())
            errorNotification.showNotification(getActivity(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }

    @Override
    public void initRecyclerView() {
        adapter = new EventsListAdapter(getActivity(), this);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);
    }
}
