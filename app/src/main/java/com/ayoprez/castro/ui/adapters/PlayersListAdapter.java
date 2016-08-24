package com.ayoprez.castro.ui.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.adapters.players.PlayerAdapterPresenter;
import com.ayoprez.castro.ui.viewholders.players.PlayersViewHolder;

import javax.inject.Inject;

/**
 * Created by ayo on 10.07.16.
 */
public class PlayersListAdapter extends RecyclerView.Adapter<PlayersViewHolder> {
    private static final String TAG = PlayersListAdapter.class.getSimpleName();

    protected Context context;

    @Inject
    PlayerAdapterPresenter playersPresenter;

    public PlayersListAdapter(Context context){
        this.context = context;
    }

    @Override
    public PlayersViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_recycler_player, parent, false);
        return new PlayersViewHolder(view);
    }

    private void initAdapterComponents() {
        ((CastroApplication) context.getApplicationContext()).getComponent().inject(this);
    }

    @Override
    public void onBindViewHolder(PlayersViewHolder holder, int position) {
        playersPresenter.setView(holder);
    }

    @Override
    public int getItemCount() {
        initAdapterComponents();

        return playersPresenter.getPlayersCountSize();
    }
}
