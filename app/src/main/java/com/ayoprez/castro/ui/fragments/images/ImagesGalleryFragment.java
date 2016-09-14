package com.ayoprez.castro.ui.fragments.images;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.images.GalleryPresenter;
import com.ayoprez.castro.ui.adapters.ImagesGalleryListAdapter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryFragment extends Fragment implements ImagesGalleryView {

    @Inject
    GalleryPresenter galleryPresenter;

    protected ImagesGalleryListAdapter adapter;

    @BindView(R.id.recyclerViewList)
    protected RecyclerView recyclerView;

    public ImagesGalleryFragment(){ }

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
        galleryPresenter.setView(this);
    }

    @Override
    public void showEmptyListMessage(byte errorMessage) {
        Snackbar.make(getView(), getResources().getStringArray(R.array.errorsArray)[errorMessage], Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void initRecyclerView() {
        adapter = new ImagesGalleryListAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void changeFragment(Fragment fragment) {
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, fragment)
                .addToBackStack("image")
                .commit();

    }
}
