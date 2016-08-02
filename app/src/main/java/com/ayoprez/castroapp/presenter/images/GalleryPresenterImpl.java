package com.ayoprez.castroapp.presenter.images;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;
import com.ayoprez.castroapp.ui.fragments.images.ImagesGalleryView;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryPresenterImpl implements GalleryPresenter{

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
            galleryView.showEmptyListMessage();
        }else {
            galleryView.initRecyclerView();
        }
    }
}
