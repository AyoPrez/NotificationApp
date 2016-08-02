package com.ayoprez.castroapp.ui.viewholders.events;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ayoprez.castroapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 19.06.16.
 */
public class EventsViewHolder extends RecyclerView.ViewHolder implements EventListItemView {

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
        eventSubtitle.setText(subtitle);
    }

    @Override
    public void displayEventImage(String image) {

    }

    @Override
    public void onEventItemClick(final int EventId) {
        eventItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Open fragment with details
            }
        });
    }

    @Override
    public void showError() {

    }

}
