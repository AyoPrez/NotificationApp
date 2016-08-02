package com.ayoprez.castroapp.ui.viewholders.sponsor;

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
public class SponsorItemViewHolder extends RecyclerView.ViewHolder implements SponsorItemView {
    private static final String TAG = SponsorItemViewHolder.class.getSimpleName();

    @BindView(R.id.iv_gallery)
    ImageView ivSponsorItem;

    public SponsorItemViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemImage(String image) {
        Picasso.with(ivSponsorItem.getContext()).load(image).into(ivSponsorItem);
    }

    @Override
    public void showError() {

    }
}
