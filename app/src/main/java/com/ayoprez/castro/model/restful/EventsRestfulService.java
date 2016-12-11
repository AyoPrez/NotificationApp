package com.ayoprez.castro.model.restful;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 20.08.16.
 */
public interface EventsRestfulService {
    void getRestfulEvents(CommonActivityView view);
    void deleteCompleteEventsData();
    boolean isComplete();
}
