package com.ayoprez.castro.robolectric.support;

import android.view.View;
import android.widget.LinearLayout;

import org.robolectric.RuntimeEnvironment;

/**
 * Created by ayo on 10.07.16.
 */
public class ResourceLocator {

    public static String getString(int stringId){
        return RuntimeEnvironment.application.getString(stringId);
    }

    public static LinearLayout.LayoutParams getViewParams(View view){
        return (LinearLayout.LayoutParams) view.getLayoutParams();
    }

    public static int getColor(int colorId){
        return RuntimeEnvironment.application.getResources().getColor(colorId);
    }

    public static int getDimen(int dimenId){
        return (int)RuntimeEnvironment.application.getResources().getDimension(dimenId);
    }

}
