package com.ayoprez.castro.common;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.widget.Toast;

/**
 * Created by ayo on 17.09.16.
 */
public class ErrorNotification {

    public void showNotification(Context context, String text){
//        Snackbar.make(view, text, Snackbar.LENGTH_LONG).show();
//        Toast.makeText(view.getContext(), text, Toast.LENGTH_LONG).show();
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context).setMessage(text).setNeutralButton("Ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
    }
}
