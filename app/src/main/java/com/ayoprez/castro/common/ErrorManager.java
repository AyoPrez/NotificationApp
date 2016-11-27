package com.ayoprez.castro.common;

import android.util.Log;

import com.ayoprez.castro.CastroApplication;
import com.crashlytics.android.Crashlytics;
import com.crashlytics.android.core.CrashlyticsCore;

import io.fabric.sdk.android.Fabric;

/**
 * Created by ayo on 23.08.16.
 */
public class ErrorManager {
    private static final String TAG = ErrorManager.class.getSimpleName();

    public static final byte ERROR_RESTFUL_IMAGES = 0;
    public static final byte ERROR_RESTFUL_VIDEOS = 1;
    public static final byte ERROR_RESTFUL_EVENTS = 2;
    public static final byte ERROR_RESTFUL_PLAYERS = 3;
    public static final byte ERROR_RESTFUL_GAMES = 4;
    public static final byte ERROR_RESTFUL_SPONSORS = 5;
    public static final byte ERROR_RESTFUL_ABOUTUS = 6;
    public static final byte ERROR_RESTFUL_ARENA = 7;

    public static final byte ERROR_EMPTY_PLAYERS = 8;
    public static final byte ERROR_EMPTY_VIDEOS = 9;
    public static final byte ERROR_EMPTY_IMAGES = 10;
    public static final byte ERROR_EMPTY_EVENTS = 11;
    public static final byte ERROR_EMPTY_SPONSORS = 12;
    public static final byte ERROR_EMPTY_ABOUTUS = 13;
    public static final byte ERROR_EMPTY_ARENA = 14;
    public static final byte ERROR_EMPTY_GAMES_TABLE = 15;
    public static final byte ERROR_EMPTY_GAMES_CALENDAR = 16;

    public static final byte ERROR_NO_DATA_ABOUTUS = 17;
    public static final byte ERROR_NO_DATA_ARENA = 17;
    public static final byte ERROR_NO_DATA_GAMES_TABLE = 19;
    public static final byte ERROR_NO_DATA_GAMES_CALENDAR = 20;
    public static final byte ERROR_NO_DATA_EVENT = 21;
    public static final byte ERROR_NO_DATA_IMAGE = 22;
    public static final byte ERROR_NO_DATA_VIDEO = 23;
    public static final byte ERROR_NO_DATA_SPONSOR = 24;
    public static final byte ERROR_NO_DATA_PLAYER = 25;
    public static final byte ERROR = 26;
    public static final byte ERROR_NO_PHONE_NUMBER = 27;

    protected void showError(CommonActivityView commonActivityView, byte message){
        commonActivityView.showErrorMessage(message);

        sendErrorLogCrashlytics("Nº " + message);
    }

    protected void showError(CommonListView commonListView, byte message){
        commonListView.showEmptyListMessage(message);

        sendErrorLogCrashlytics("Nº " + message);
    }

    protected void showError(CommonListItemView commonListItemView, byte message, int position){
        commonListItemView.showErrorMessage(message, position);

        sendErrorLogCrashlytics("Nº " + message);
    }

    public void sendException(Throwable throwable){
        Crashlytics.logException(throwable);
    }

    private void sendErrorLogCrashlytics(String error){
        try {
            if (CrashlyticsCore.getInstance() != null) {
                CrashlyticsCore.getInstance().log(error);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

}
