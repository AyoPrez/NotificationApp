package com.ayoprez.castro.presenter;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.ui.MainView;

import javax.inject.Inject;

/**
 * Created by ayo on 03.07.16.
 */
public class MainPresenterImpl implements MainPresenter {
    private static final String TAG = MainPresenterImpl.class.getSimpleName();

    @Inject
    ImagesGalleryRepository imagesGalleryRepository;

    public MainPresenterImpl(ImagesGalleryRepository imagesGalleryRepository){
        this.imagesGalleryRepository = imagesGalleryRepository;
    }

    @Override
    public void initGallery(MainView view) {
        if(view != null) {
            view.initImageGallery(imagesGalleryRepository.getAllStringImages());
        }else{
            throw new ViewNotFoundException();
        }
    }
}
