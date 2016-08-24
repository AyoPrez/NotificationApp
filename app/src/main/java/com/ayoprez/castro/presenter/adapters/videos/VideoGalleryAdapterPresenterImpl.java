package com.ayoprez.castro.presenter.adapters.videos;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.ui.viewholders.videos.VideoItemView;

/**
 * Created by ayo on 18.08.16.
 */
public class VideoGalleryAdapterPresenterImpl extends ErrorManager implements VideoGalleryAdapterPresenter {
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
            showError(view, ERROR_EMPTY_VIDEOS, itemPosition);
        }else{
            applyDisplayVideoPreview(item.getMeta().getPreview());
        }
    }

    private void applyDisplayVideoPreview(String image){
        if(image == null || image.isEmpty()){
            showError(view, ERROR_NO_DATA_VIDEO, view.getItemPosition());
        }else {
            view.displayItemPreview(image);
        }
    }

    @Override
    public int getVideosCountSize() {
        return repository.getAllVideos().size();
    }
}
