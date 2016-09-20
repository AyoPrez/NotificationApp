package com.ayoprez.castro.common;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class DeviceBootReceiver extends BroadcastReceiver {

    private static final String LOG_TAG = DeviceBootReceiver.class.getSimpleName();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
           reSchedule(context);
        }else{
            Log.e("", "It is not a reboot intent");
        }
    }

    //TODO
    public void reSchedule(Context context){

    }
}