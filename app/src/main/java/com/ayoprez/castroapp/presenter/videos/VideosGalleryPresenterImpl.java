package com.ayoprez.castroapp.presenter.videos;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.repository.VideosGalleryRepository;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryFragment;
import com.ayoprez.castroapp.ui.fragments.videos.VideosGalleryView;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryPresenterImpl implements VideosGalleryPresenter {
    private static final String TAG = VideosGalleryPresenterImpl.class.getSimpleName();

    protected VideosGalleryRepository repository;
    protected VideosGalleryView view;


    public VideosGalleryPresenterImpl(VideosGalleryRepository videosRepository){
        this.repository = videosRepository;
    }

    @Override
    public void setView(VideosGalleryView view) {
        this.view = view;
        initView();
    }

    private void initView(){
        if(view == null){
            throw new ViewNotFoundException();
        }

        if(repository.getAllVideos().size() <= 0){
            view.showEmptyListMessage();
        }else {
            view.initRecyclerView();
        }
    }
}
