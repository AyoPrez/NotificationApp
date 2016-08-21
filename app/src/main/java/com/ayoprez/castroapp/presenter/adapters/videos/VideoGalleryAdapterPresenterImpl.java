package com.ayoprez.castroapp.presenter.adapters.videos;

import android.content.Context;

import com.ayoprez.castroapp.CastroApplication;
import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.models.VideoItem;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryView;
import com.ayoprez.castroapp.ui.viewholders.videos.VideoItemView;

/**
 * Created by ayo on 18.08.16.
 */
public class VideoGalleryAdapterPresenterImpl implements VideoGalleryAdapterPresenter {
    private static final String TAG = VideoGalleryAdapterPresenterImpl.class.getSimpleName();

    protected VideosGalleryRepository repository;
    protected VideoItemView view;
    protected VideoItem item;

    public VideoGalleryAdapterPresenterImpl(VideosGalleryRepository videosRepository) {
        this.repository = videosRepository;
    }

    @Override
    public void setView(VideoItemView view) {
        this.view = view;
        loadVideos();
    }

    @Override
    public void loadVideos() {
        if(view == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = view.getItemPosition();
        item = repository.getVideo(itemPosition);

        if(item == null){
            view.showError();
        }else{
            applyDisplayVideoPreview(item.getMeta().getPreview());
        }
    }

    private void applyDisplayVideoPreview(String image){
        if(image == null || image.isEmpty()){
            view.showError();
        }else {
            view.displayItemPreview(image);
        }
    }

    @Override
    public int getVideosCountSize() {
        return repository.getAllVideos().size();
    }
}
