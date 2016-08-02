package com.ayoprez.castroapp.ui.fragments.arena;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.arena.ArenaPresenter;
import com.squareup.picasso.Picasso;

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

    @BindView(R.id.iv_arena)
    ImageView ivArena;
    @BindView(R.id.tv_address_arena)
    TextView tvArenaAdress;
    @BindView(R.id.tv_description_arena)
    TextView tvArenaDescription;
    @BindView(R.id.tv_name_arena)
    TextView tvArenaName;
    @BindView(R.id.button_map_arena)
    Button btnArenaMap;

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
        Picasso.with(getActivity()).load(image).fit().into(ivArena);
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
        Uri gmmIntentUri = Uri.parse("google.streetview:cbll=46.414382,10.013988");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
        mapIntent.setPackage("com.google.android.apps.maps");
        startActivity(mapIntent);
    }

    @Override
    public void showErrorMessage() {
        Toast.makeText(getActivity(), "Change this for anothing else", Toast.LENGTH_LONG).show();
    }
}
