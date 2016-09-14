package com.ayoprez.castro.presenter.videos;

import android.os.Bundle;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.presenter.adapters.videos.VideoGalleryAdapterPresenter;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.ui.fragments.WebViewFragment;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryView;
import com.ayoprez.castro.ui.viewholders.videos.VideoItemView;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryPresenterImpl extends ErrorManager implements VideosGalleryPresenter, VideoGalleryAdapterPresenter {
    private static final String TAG = VideosGalleryPresenterImpl.class.getSimpleName();

    protected VideosGalleryRepository repository;
    protected static VideosGalleryView galleryView;
    protected VideoItemView itemView;
    protected VideoItem item;


    public VideosGalleryPresenterImpl(VideosGalleryRepository videosRepository){
        this.repository = videosRepository;
    }

    @Override
    public void setView(VideosGalleryView view) {
        if(galleryView == null) {
            galleryView = view;
        }
        initView();
    }

    private void initView(){
        if(galleryView == null){
            throw new ViewNotFoundException();
        }


        if(repository.getAllVideos().size() <= 0){
            showError(galleryView, ERROR_EMPTY_VIDEOS);
        }else {
            try {
                galleryView.initRecyclerView();
            }catch (Exception e){
                showError(galleryView, ERROR);
            }
        }
    }

    @Override
    public void setView(VideoItemView view) {
        this.itemView = view;
        loadVideos();
    }

    @Override
    public void loadVideos() {
        if(itemView == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = itemView.getItemPosition();
        item = repository.getVideo(itemPosition);

        if(item == null){
            showError(itemView, ERROR_EMPTY_VIDEOS, itemPosition);
        }else{
            applyDisplayVideoPreview(item.getMeta().getPreview());
        }
    }

    @Override
    public void openVideo(int position) {
        VideoItem item = repository.getItemByPosition(position);

        if(item == null || item.getTitle() == null || item.getTitle().trim().equals("")) {
            showError(itemView, ERROR_EMPTY_VIDEOS, position);
        }else{
            WebViewFragment webViewFragment = new WebViewFragment();

            String url = item.getMeta().getVideo();

            Bundle bundle = new Bundle();
            bundle.putString("url", url);

            webViewFragment.setArguments(bundle);

            if(galleryView != null) {
                galleryView.changeFragment(webViewFragment);
            }
        }
    }

    private void applyDisplayVideoPreview(String image){
        if(image == null || image.isEmpty()){
            showError(itemView, ERROR_NO_DATA_VIDEO, itemView.getItemPosition());
        }else {
            itemView.displayItemPreview(image);
        }
    }

    @Override
    public int getVideosCountSize() {
        return repository.getAllVideos().size();
    }
}
