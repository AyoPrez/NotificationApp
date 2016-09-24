package com.ayoprez.castro.ui.fragments.arena;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.arena.ArenaPresenter;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 03.07.16.
 */
public class ArenaFragment extends Fragment implements ArenaView {
    private static final String TAG = ArenaFragment.class.getSimpleName();

    @Inject
    ArenaPresenter arenaPresenter;

    @Inject
    ImageLib imageLib;

    @Inject
    ErrorNotification errorNotification;

    @BindView(R.id.iv_arena)
    ImageView ivArena;
    @BindView(R.id.tv_address_arena)
    TextView tvArenaAdress;
    @BindView(R.id.tv_description_arena)
    TextView tvArenaDescription;
    @BindView(R.id.tv_name_arena)
    TextView tvArenaName;
    @BindView(R.id.button_map_arena)
    ImageButton btnArenaMap;

    public static ArenaFragment instance;

    public static ArenaFragment getInstance() {
        if(instance == null){
            instance = new ArenaFragment();
        }

        return instance;
    }

    public ArenaFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_arena, container, false);
        ButterKnife.bind(this, view);
        arenaPresenter.setView(this);
        return view;
    }

    @Override
    public void displayName(String name) {
        tvArenaName.setText(name);
    }

    @Override
    public void displayAddress(String address) {
        tvArenaAdress.setText(address);
    }

    @Override
    public void displayDescription(String description) {
        tvArenaDescription.setText(description);
    }

    @Override
    public void displayImage(String image) {
        imageLib.setImageIntoView(image, ivArena);
    }

    @Override
    public void clickMapButton(final String address) {
        btnArenaMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arenaPresenter.openMap(address);
            }
        });
    }

    @Override
    public void displayMap(String address) {
        Uri gmmIntentUri = Uri.parse("geo:0,0?q=" + address);
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        if (isAdded())
            errorNotification.showNotification(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }
}
