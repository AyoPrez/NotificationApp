package com.ayoprez.castroapp.ui.viewholders.images;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ayoprez.castroapp.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryItemViewHolder extends RecyclerView.ViewHolder implements GalleryItemView {
    private static final String TAG = GalleryItemViewHolder.class.getSimpleName();


    @BindView(R.id.iv_gallery)
    ImageView ivGalleryItem;

    public GalleryItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemImage(String image) {
        Picasso.with(ivGalleryItem.getContext()).load(image).into(ivGalleryItem);
    }

    @Override
    public void showError() {

    }
}
