package com.ayoprez.castro.ui.fragments.aboutus;

import com.ayoprez.castro.common.CommonActivityView;

/**
 * Created by ayo on 03.07.16.
 */
public interface AboutUsView extends CommonActivityView {

    void displayName(String name);
    void displayDescription(String description);
    void displayImage(String image);

    void clickShareButton();
    void openShare(String shareText);

    void clickCallButton();
    void openPhone(String number);

    void clickMailButton();
    void openMail(String mail);
}
