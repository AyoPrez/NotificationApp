package com.ayoprez.castro.common;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ayo on 17.09.16.
 */
public class ErrorNotification {

    public void showNotification(View view, String text){
        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
    }
}
