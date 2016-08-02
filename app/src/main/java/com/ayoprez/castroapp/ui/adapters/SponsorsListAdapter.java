package com.ayoprez.castroapp.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.R;
import com.ayoprez.castroapp.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castroapp.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemViewHolder;
import com.ayoprez.castroapp.ui.viewholders.sponsor.SponsorItemViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsListAdapter extends RecyclerView.Adapter<SponsorItemViewHolder> {
    private static final String TAG = SponsorsListAdapter.class.getSimpleName();

    protected Context context;

    @Inject
    SponsorListAdapterPresenter presenter;

    @Override
    public SponsorItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        this.context = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, parent, false);
        return new SponsorItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SponsorItemViewHolder holder, int position) {
        initAdapterComponents();
        presenter.setView(holder);
    }

    private void initAdapterComponents() {
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public int getItemCount() {
        return presenter.getSponsorsCountSize();
    }
}
