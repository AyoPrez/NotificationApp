package com.ayoprez.castroapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryListAdapter extends RecyclerView.Adapter<GalleryItemViewHolder> {
    private static final String TAG = ImagesGalleryListAdapter.class.getSimpleName();

    @Inject
    GalleryAdapterPresenter presenter;

    public ImagesGalleryListAdapter(Context context){
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public GalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, parent, false);
        return new GalleryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(GalleryItemViewHolder holder, int position) {
        presenter.setView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getImagesCountSize();
    }
}
