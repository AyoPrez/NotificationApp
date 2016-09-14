package com.ayoprez.castro.presenter.images;

import android.os.Bundle;

import com.ayoprez.castro.ViewNotFoundException;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.presenter.adapters.images.GalleryAdapterPresenter;
import com.ayoprez.castro.repository.ImagesGalleryRepository;
import com.ayoprez.castro.ui.fragments.ImageFragment;
import com.ayoprez.castro.ui.fragments.images.ImagesGalleryView;
import com.ayoprez.castro.ui.viewholders.images.GalleryItemView;

/**
 * Created by ayo on 17.07.16.
 */
public class GalleryPresenterImpl extends ErrorManager implements GalleryPresenter, GalleryAdapterPresenter {

    private ImagesGalleryRepository repository;
    private static ImagesGalleryView galleryView;
    protected GalleryItemView itemView;
    protected String item;

    public GalleryPresenterImpl(ImagesGalleryRepository repository){
        this.repository = repository;
    }

    @Override
    public void setView(ImagesGalleryView view) {
        if(galleryView == null) {
            galleryView = view;
        }
        initView();
    }

    private void initView(){
        if(galleryView == null){
            throw new ViewNotFoundException();
        }

        if(repository.getAllStringImages().size() <= 0){
            showError(galleryView, ERROR_EMPTY_IMAGES);
        }else {
            try {
                galleryView.initRecyclerView();
            }catch(Exception e){
                showError(galleryView, ERROR);
            }
        }
    }

    @Override
    public void setView(GalleryItemView view) {
        this.itemView = view;
        loadImages();
    }

    @Override
    public void loadImages() {
        if(itemView == null){
            throw new ViewNotFoundException();
        }

        int itemPosition = itemView.getItemPosition();
        item = repository.getImage(itemPosition);

        if(item == null){
            showError(itemView, ERROR_EMPTY_IMAGES, itemPosition);
        }else{
            applyDisplayImages(item);
        }
    }

    private void applyDisplayImages(String image){
        if(image == null || image.isEmpty()){
            showError(itemView, ERROR_NO_DATA_IMAGE, itemView.getItemPosition());
        }else {
            itemView.displayItemImage(image);
        }
    }

    @Override
    public int getImagesCountSize() {
        return repository.getAllStringImages().size();
    }

    @Override
    public void openImage(int position) {
        String url = repository.getImageItemByPosition(position).getMeta().getPhoto();

        ImageFragment imageFragment = new ImageFragment();
        Bundle bundle = new Bundle();
        bundle.putString("image", url);

        imageFragment.setArguments(bundle);

        galleryView.changeFragment(imageFragment);
    }
}
