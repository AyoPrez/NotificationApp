package com.ayoprez.castro.presenter.images;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.ui.fragments.images.ImagesGalleryView;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryPresenterImpl extends ErrorManager implements GalleryPresenter{

    private ImagesGalleryRepository repository;
    private ImagesGalleryView galleryView;

    public GalleryPresenterImpl(ImagesGalleryRepository repository){
        this.repository = repository;
    }

    @Override
    public void setView(ImagesGalleryView view) {
        this.galleryView = view;
        initView();
    }

    private void initView(){
        if(galleryView == null){
            throw new ViewNotFoundException();
        }

        if(repository.getAllImages().size() <= 0){
            showError(galleryView, ERROR_EMPTY_IMAGES);
        }else {
            galleryView.initRecyclerView();
        }
    }
}
