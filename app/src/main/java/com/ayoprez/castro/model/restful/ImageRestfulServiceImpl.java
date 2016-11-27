package com.ayoprez.castro.model.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.model.models.ImageItem;
import com.ayoprez.castro.model.repository.ImagesGalleryRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 19.08.16.
 */
public class ImageRestfulServiceImpl extends ErrorManager implements ImageRestfulService {
    private static final String TAG = ImageRestfulServiceImpl.class.getSimpleName();

    private ImagesGalleryRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public ImageRestfulServiceImpl(ImagesGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulImages(final CommonActivityView view) {

        subscription = service.getImagesFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<ImageItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompleteImagesData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        view.showErrorMessage(ERROR_RESTFUL_IMAGES);
//                        showError(view, ERROR_RESTFUL_IMAGES);
                    }

                    @Override
                    public void onNext(ArrayList<ImageItem> images) {
                        repository.saveImages(images);
                    }
                });
    }

    @Override
    public void deleteCompleteImagesData() {
        if(repository.getAllStringImages().size() > 0){
            repository.deleteAllImages();
        }
    }
}
