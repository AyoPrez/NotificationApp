package com.ayoprez.castro.ui.viewholders.videos;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryItemViewHolder extends RecyclerView.ViewHolder implements VideoItemView {
    private static final String TAG = VideosGalleryItemViewHolder.class.getSimpleName();

    @Inject
    ImageLib imageLib;

    @BindView(R.id.iv_gallery)
    ImageView ivGalleryVideo;
    @BindView(R.id.videoTitle)
    TextView tvVideoTitle;

    public VideosGalleryItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((CastroApplication)itemView.getContext().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemPreview(String image) {
        imageLib.setImageIntoView(image, ivGalleryVideo);
    }

    @Override
    public void displayItemTitle(String title) {
        tvVideoTitle.setText(title);
    }

    @Override
    public void showErrorMessage(byte message, int position) {

    }
}
