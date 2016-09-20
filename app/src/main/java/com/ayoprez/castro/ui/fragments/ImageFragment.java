package com.ayoprez.castro.ui.fragments;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.ui.MainActivity;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * Created by ayo on 12.09.16.
 */
public class ImageFragment extends Fragment {

    @Inject
    ImageLib imageLib;

    @BindView(R.id.imageFragment)
    ImageView imageView;

    protected PhotoViewAttacher photoZoom;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_imageview, container, false);
        ButterKnife.bind(this, v);

        String url = getArguments().getString("image");

        ((MainActivity)getActivity()).getToolbar().setVisibility(View.GONE);

        imageLib.setImageIntoView(url, imageView);
        photoZoom = new PhotoViewAttacher(imageView);

        return v;
    }
}
