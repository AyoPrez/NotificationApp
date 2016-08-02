package com.ayoprez.castroapp.ui.viewholders.events;

/**
 * Created by ayo on 26.06.16.
 */
public interface EventListItemView {
    int getEventPosition();

    void displayEventTitle(String title);
    void displayEventSubtitle(String subtitle);
    void displayEventImage(String image);

    void onEventItemClick(int EventId);

    void showError(); //TODO how?

}
