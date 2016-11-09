package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.models.VideoItem;
import com.ayoprez.castro.repository.VideosGalleryRepository;

import java.util.ArrayList;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by ayo on 19.08.16.
 */
public class VideoRestfulServiceImpl extends ErrorManager implements VideoRestfulService {
    private static final String TAG = VideoRestfulServiceImpl.class.getSimpleName();

    private VideosGalleryRepository repository;
    private RestfulService service;
    private Subscription subscription;

    public VideoRestfulServiceImpl(VideosGalleryRepository repository, RestfulService service){
        this.repository = repository;
        this.service = service;
    }

    @Override
    public void getRestfulVideos(final CommonActivityView view) {

        subscription = service.getVideosFromServer()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ArrayList<VideoItem>>() {
                    @Override
                    public void onCompleted() {
                    }

                    @Override
                    public void onStart() {
                        deleteCompleteVideoData();
                    }

                    @Override
                    public void onError(Throwable e) {
                        showError(view, ERROR_RESTFUL_VIDEOS);
                    }

                    @Override
                    public void onNext(ArrayList<VideoItem> videos) {
                        repository.saveVideos(videos);
                    }
                });
    }

    @Override
    public void deleteCompleteVideoData() {
        if(repository.getAllVideos().size() > 0){
            repository.deleteAllVideos();
        }
    }
}
