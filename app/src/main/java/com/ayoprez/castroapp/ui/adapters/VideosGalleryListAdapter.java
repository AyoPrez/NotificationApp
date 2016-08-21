package com.ayoprez.castroapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castroapp.ui.viewholders.videos.VideosGalleryItemViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryListAdapter extends RecyclerView.Adapter<VideosGalleryItemViewHolder>{
    private static final String TAG = VideosGalleryListAdapter.class.getSimpleName();

    @Inject
    VideoGalleryAdapterPresenter presenter;

    public VideosGalleryListAdapter(Context context){
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public VideosGalleryItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, parent, false);
        return new VideosGalleryItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(VideosGalleryItemViewHolder holder, int position) {
        presenter.setView(holder);
    }

    @Override
    public int getItemCount() {
        return presenter.getVideosCountSize();
    }
}
