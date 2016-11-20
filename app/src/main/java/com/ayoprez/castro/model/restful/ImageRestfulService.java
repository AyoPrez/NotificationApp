package com.ayoprez.castro.model.restful;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 19.08.16.
 */
public interface ImageRestfulService {
    void getRestfulImages(CommonActivityView view);
    void deleteCompleteImagesData();
}
