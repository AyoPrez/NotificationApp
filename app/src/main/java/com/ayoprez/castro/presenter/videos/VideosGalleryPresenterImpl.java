package com.ayoprez.castro.presenter.videos;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.repository.VideosGalleryRepository;
import com.ayoprez.castro.ui.fragments.videos.VideosGalleryView;

/**
 * Created by ayo on 18.08.16.
 */
public class VideosGalleryPresenterImpl extends ErrorManager implements VideosGalleryPresenter {
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
            showError(view, ERROR_EMPTY_VIDEOS);
        }else {
            view.initRecyclerView();
        }
    }
}
