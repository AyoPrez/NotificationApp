package com.ayoprez.castro.ui.viewholders.events;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 19.06.16.
 */
public class EventsViewHolder extends RecyclerView.ViewHolder implements EventListItemView {

    @Inject
    ImageLib imageLib;

    @BindView(R.id.eventItem)
    public LinearLayout eventItem;
    @BindView(R.id.eventImage)
    public ImageView eventImage;
    @BindView(R.id.eventTitle)
    public TextView eventTitle;
    @BindView(R.id.eventSubtitle)
    public TextView eventSubtitle;


    public EventsViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
        ((CastroApplication)itemView.getContext().getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public int getEventPosition() {
        return getAdapterPosition();
    }

    @Override
    public void displayEventTitle(String title) {
        eventTitle.setText(title);
    }

    @Override
    public void displayEventSubtitle(String subtitle) {
        if( subtitle == null || subtitle.equals("")) {
            eventSubtitle.setVisibility(View.GONE);
        }else{
            eventSubtitle.setText(subtitle);
        }
    }

    @Override
    public void displayEventImage(String image) {
        imageLib.setImageIntoView(image, eventImage);
    }

    @Override
    public void showErrorMessage(byte message, int position) {

    }
}
