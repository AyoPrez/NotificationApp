package com.ayoprez.castroapp.ui.viewholders.videos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ayoprez.castroapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryItemViewHolder extends RecyclerView.ViewHolder implements VideoItemView {
    private static final String TAG = VideosGalleryItemViewHolder.class.getSimpleName();

    @BindView(R.id.iv_gallery)
    ImageView ivGalleryVideo;

    public VideosGalleryItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemPreview(String image) {
        Picasso.with(ivGalleryVideo.getContext()).load(image).into(ivGalleryVideo);
    }

    @Override
    public void showError() {

    }
}
