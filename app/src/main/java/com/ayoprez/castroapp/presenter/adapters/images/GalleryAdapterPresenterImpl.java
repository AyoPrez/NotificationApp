package com.ayoprez.castroapp.presenter.adapters.images;

import com.ayoprez.castroapp.ViewNotFoundException;
import com.ayoprez.castroapp.repository.ImagesGalleryRepository;
import com.ayoprez.castroapp.ui.viewholders.images.GalleryItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryAdapterPresenterImpl implements GalleryAdapterPresenter {
    private static final String TAG = GalleryAdapterPresenterImpl.class.getSimpleName();

    GalleryItemView view;
    ImagesGalleryRepository repository;
    String item;

    public GalleryAdapterPresenterImpl(ImagesGalleryRepository imagesGalleryRepository){
        this.repository = imagesGalleryRepository;
    }

    @Override
    public void setView(GalleryItemView view) {
        this.view = view;
        loadImages();
    }

    @Override
    public void loadImages() {
        if(view == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = view.getItemPosition();
        item = repository.getImage(itemPosition);

        if(item == null){
            view.showError();
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(String image){
        if(image == null || image.isEmpty()){
            view.showError();
        }else {
            view.displayItemImage(image);
        }
    }

    @Override
    public int getImagesCountSize() {
        return repository.getAllImages().size();
    }

}
