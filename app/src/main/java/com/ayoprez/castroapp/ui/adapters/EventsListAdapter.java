package com.ayoprez.castroapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.adapters.events.EventAdapterPresenter;
import com.ayoprez.castroapp.ui.viewholders.events.EventsViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 19.06.16.
 */
public class EventsListAdapter extends RecyclerView.Adapter<EventsViewHolder> {

    private Context context;

    @Inject
    EventAdapterPresenter presenter;

    @Override
    public EventsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_event, parent, false);
        return new EventsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final EventsViewHolder holder, int position) {
        initAdapterComponents();
        presenter.setView(holder);
    }

    private void initAdapterComponents() {
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public int getItemCount() {
        return presenter.getEventsCountSize();
    }
}