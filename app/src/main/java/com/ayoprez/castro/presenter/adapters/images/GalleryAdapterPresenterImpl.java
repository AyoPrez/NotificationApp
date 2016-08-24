package com.ayoprez.castro.presenter.adapters.images;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.ui.viewholders.images.GalleryItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryAdapterPresenterImpl extends ErrorManager implements GalleryAdapterPresenter {
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
            showError(view, ERROR_EMPTY_IMAGES, itemPosition);
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(String image){
        if(image == null || image.isEmpty()){
            showError(view, ERROR_NO_DATA_IMAGE, view.getItemPosition());
        }else {
            view.displayItemImage(image);
        }
    }

    @Override
    public int getImagesCountSize() {
        return repository.getAllImages().size();
    }

}
