package com.ayoprez.castro.ui.fragments.sponsors;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.presenter.sponsors.SponsorsPresenter;
import com.ayoprez.castro.ui.adapters.SponsorsListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsFragment extends Fragment implements SponsorsView {

    @Inject
    SponsorsPresenter sponsorsPresenter;

    @Inject
    ErrorNotification errorNotification;

    protected SponsorsListAdapter adapter;

    @BindView(R.id.recyclerViewList)
    protected RecyclerView recyclerView;

    public static SponsorsFragment instance;

    public static SponsorsFragment getInstance() {
        if (instance == null){
            instance = new SponsorsFragment();
        }

        return instance;
    }

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
    public void showEmptyListMessage(byte errorMessage) {
        if (isAdded())
            errorNotification.showNotification(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }

    @Override
    public void initRecyclerView() throws Exception{
        adapter = new SponsorsListAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("sponsor")
                .commit();
    }
}
