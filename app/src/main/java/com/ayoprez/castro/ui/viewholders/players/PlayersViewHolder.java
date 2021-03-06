package com.ayoprez.castro.ui.viewholders.players;

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
 * Created by ayo on 10.07.16.
 */
public class PlayersViewHolder extends RecyclerView.ViewHolder implements PlayerListItemView {

    @Inject
    ImageLib imageLib;

    @BindView(R.id.playerImage)
    public ImageView playerImage;
    @BindView(R.id.playerName)
    public TextView playerName;

    public PlayersViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((CastroApplication)itemView.getContext().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public void displayItemTitle(String title) {
        playerName.setText(title);
    }

    @Override
    public void displayItemImage(String image) {
        imageLib.setImageIntoView(image, playerImage);
    }

    @Override
    public void showErrorMessage(byte message, int position) {

    }
}
