package com.ayoprez.castro.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castro.ui.viewholders.images.GalleryItemViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 17.07.16.
 */
public class ImagesGalleryListAdapter extends RecyclerView.Adapter<GalleryItemViewHolder> {

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
    public void onBindViewHolder(final GalleryItemViewHolder holder, int position) {
        presenter.setView(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openImage(holder.getItemPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getImagesCountSize();
    }
}
