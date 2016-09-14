package com.ayoprez.castro.ui.viewholders.sponsor;

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
public class SponsorItemViewHolder extends RecyclerView.ViewHolder implements SponsorItemView {
    private static final String TAG = SponsorItemViewHolder.class.getSimpleName();

    @Inject
    ImageLib imageLib;

    @BindView(R.id.iv_gallery)
    ImageView ivSponsorItem;

    public SponsorItemViewHolder(View itemView) {
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
        imageLib.setImageIntoViewWithPlaceHolder(image, ivSponsorItem, R.drawable.ic_menu_gallery);
    }

    @Override
    public void showErrorMessage(byte message, int position) {

    }
}
