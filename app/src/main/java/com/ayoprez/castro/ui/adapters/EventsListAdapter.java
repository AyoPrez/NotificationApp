package com.ayoprez.castro.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castro.presenter.events.EventPresenter;
import com.ayoprez.castro.ui.viewholders.events.EventsViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 19.06.16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    @Inject
    EventAdapterPresenter presenter;

    public EventsListAdapter(Context context){
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_event, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, int position) {
        presenter.setListItemView(holder);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.openDetailedView(holder.getEventPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getEventsCountSize();
    }
}