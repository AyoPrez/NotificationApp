package com.ayoprez.castro.common;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.models.NotificationEvents;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepositoryImpl;

import javax.inject.Inject;

/**
 * Created by ayo on 17.09.16.
 */
public class AlarmNotificationReceiver extends BroadcastReceiver {
    public static String NOTIFICATION_ID = "notification-id";

    @Override
    public void onReceive(Context context, Intent intent) {

        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);

        int id = intent.getIntExtra(NOTIFICATION_ID, 0);

        Notification notification = getNotificationLogic(context, intent.getStringExtra("content"), intent.getShortExtra("id", (short)0));
        notification.flags |= Notification.FLAG_SHOW_LIGHTS;
        notification.ledARGB = 0xff00ff00;
        notification.ledOnMS = 300;
        notification.ledOffMS = 1000;

        notificationManager.notify(id, notification);
    }

    protected PendingIntent getPendingIntentLogic(Context context, short eventId){
        String action = "EVENT";

        // Create a pending intent to open the the application when the notification is clicked.
        //Restart the app.
        Intent launchIntent = context.getPackageManager().getLaunchIntentForPackage(context.getPackageName());

        Bundle bundle = new Bundle();
        bundle.putShort("eventId", eventId);

        if(launchIntent != null){
            launchIntent.putExtras(bundle);
            launchIntent.putExtra("action", action);
        }

        return PendingIntent.getActivity(context, 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);
    }

    protected Notification getNotificationLogic(Context context, String content, short eventId){

        NotificationEventsRepository notificationEventsRepository = new NotificationEventsRepositoryImpl();

        notificationEventsRepository.deleteNotificationEventByEventId(eventId);

        PendingIntent pendingIntent = getPendingIntentLogic(context, eventId);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context);
        NotificationCompat.BigTextStyle bigText = new NotificationCompat.BigTextStyle();
        bigText.bigText(content);
        bigText.setBigContentTitle(context.getString(R.string.app_name));
        builder.setStyle(bigText);
        builder.setContentText(content);
        builder.setContentTitle(context.getString(R.string.remind_title));
        builder.setSmallIcon(R.drawable.ic_stat_escudo_castro_blanco_1);
        builder.setContentIntent(pendingIntent);
        builder.setAutoCancel(true);

        return builder.build();
    }
}
