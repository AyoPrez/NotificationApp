package com.ayoprez.castro.robolectric.support;

import android.app.Activity;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

/**
 * Created by ayo on 10.07.16.
 */
public class ViewLocator {

    public static TextView getTextView(Activity activity, int resource){
        return (TextView) activity.findViewById(resource) ;
    }

    public static Button getButton(Activity activity, int resource){
        return (Button) activity.findViewById(resource) ;
    }

    public static ImageButton getImageButton(Activity activity, int resource){
        return (ImageButton) activity.findViewById(resource) ;
    }

}
