package com.ayoprez.castro.ui.viewholders.players;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.castro.R;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersViewHolder extends RecyclerView.ViewHolder implements PlayerListItemView {


    @BindView(R.id.playerImage)
    public ImageView playerImage;
    @BindView(R.id.playerName)
    public TextView playerName;

    public PlayersViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    @Override
    public int getItemPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayItemTitle(String title) {
        playerName.setText(title);
    }

    @Override
    public void displayItemImage(String image) {
        Picasso.with(playerImage.getContext()).load(image).into(playerImage);
    }

    @Override
    public void showErrorMessage(String message, int position) {

    }
}
