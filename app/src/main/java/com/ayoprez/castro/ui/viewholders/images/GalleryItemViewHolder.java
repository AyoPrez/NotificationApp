package com.ayoprez.castro.ui.viewholders.images;

import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryItemViewHolder extends RecyclerView.ViewHolder implements GalleryItemView {
    private static final String TAG = GalleryItemViewHolder.class.getSimpleName();

    @Inject
    ImageLib imageLib;

    @BindView(R.id.iv_gallery)
    ImageView ivGalleryItem;

    public GalleryItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((CastroApplication)itemView.getContext().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemImage(String image) {
        imageLib.setImageIntoView(image, ivGalleryItem);
    }

    @Override
    public void showErrorMessage(byte message, int position) {

    }
}
