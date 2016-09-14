package com.ayoprez.castro.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.adapters.sponsors.SponsorListAdapterPresenter;
import com.ayoprez.castro.ui.viewholders.sponsor.SponsorItemViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 17.07.16.
 */
public class SponsorsListAdapter extends RecyclerView.Adapter<SponsorItemViewHolder> {
    private static final String TAG = SponsorsListAdapter.class.getSimpleName();

    @Inject
    SponsorListAdapterPresenter presenter;

    public SponsorsListAdapter(Context context) throws Exception{
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public SponsorItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_image, parent, false);
        return new SponsorItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final SponsorItemViewHolder holder, int position) {
        presenter.setItemView(holder);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                presenter.loadUrl(holder.getItemPosition());
            }
        });
    }

    @Override
    public int getItemCount() {
        return presenter.getSponsorsCountSize();
    }
}
