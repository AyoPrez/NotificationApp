package com.ayoprez.castroapp.presenter.adapters.videos;

import com.ayoprez.castroapp.ui.viewholders.videos.VideoItemView;

/**
 * Created by ayo on 18.08.16.
 */
public interface VideoGalleryAdapterPresenter {

    void setView(VideoItemView view);
    void loadVideos();

    int getVideosCountSize();
}
