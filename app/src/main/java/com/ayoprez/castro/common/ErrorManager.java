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

    public static final String ERROR_RESTFUL_IMAGES = "";
    public static final String ERROR_RESTFUL_VIDEOS = ""; 
    public static final String ERROR_RESTFUL_EVENTS = "";
    public static final String ERROR_RESTFUL_PLAYERS = "";
    public static final String ERROR_RESTFUL_GAMES = "";
    public static final String ERROR_RESTFUL_SPONSORS = "";
    public static final String ERROR_RESTFUL_ABOUTUS = "";
    public static final String ERROR_RESTFUL_ARENA = "";

    public static final String ERROR_EMPTY_PLAYERS = "";
    public static final String ERROR_EMPTY_VIDEOS = "";
    public static final String ERROR_EMPTY_IMAGES = "";
    public static final String ERROR_EMPTY_EVENTS = "";
    public static final String ERROR_EMPTY_SPONSORS = "";
    public static final String ERROR_EMPTY_ABOUTUS = "";
    public static final String ERROR_EMPTY_ARENA = "";
    public static final String ERROR_EMPTY_GAMES_TABLE = "";
    public static final String ERROR_EMPTY_GAMES_CALENDAR = "";

    public static final String ERROR_NO_DATA_ABOUTUS = "";
    public static final String ERROR_NO_DATA_ARENA = "";
    public static final String ERROR_NO_DATA_GAMES_TABLE = "";
    public static final String ERROR_NO_DATA_GAMES_CALENDAR = "";
    public static final String ERROR_NO_DATA_EVENT = "";
    public static final String ERROR_NO_DATA_IMAGE = "";
    public static final String ERROR_NO_DATA_VIDEO = "";
    public static final String ERROR_NO_DATA_SPONSOR = "";
    public static final String ERROR_NO_DATA_PLAYER = "";

    protected void showError(CommonActivityView commonActivityView, String message){
        commonActivityView.showErrorMessage(message);

        sendErrorLogCrashlytics(message);
    }

    protected void showError(CommonListView commonListView, String message){
        commonListView.showEmptyListMessage(message);

        sendErrorLogCrashlytics(message);
    }

    protected void showError(CommonListItemView commonListItemView, String message, int position){
        commonListItemView.showErrorMessage(message, position);

        sendErrorLogCrashlytics(message);
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
