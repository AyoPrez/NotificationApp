package com.ayoprez.castro.restful;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 19.08.16.
 */
public interface VideoRestfulService {
    void getRestfulVideos(CommonActivityView view);
    void deleteCompleteVideoData();
}
