package com.ayoprez.castro.common;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.R;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.models.NotificationEvents;
import com.ayoprez.castro.repository.EventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepository;
import com.ayoprez.castro.repository.NotificationEventsRepositoryImpl;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeviceBootReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = DeviceBootReceiver.class.getSimpleName();

    @Inject
    EventsRepository eventsRepository;

    @Inject
    TimeUtils timeUtils;

    @Override
    public void onReceive(Context context, Intent intent) {

        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
           reSchedule(context);
        }else{
            Log.e("", "It is not a reboot intent");
        }
    }

    public void reSchedule(Context context){
        NotificationEventsRepository notificationEventsRepository = new NotificationEventsRepositoryImpl();

        ArrayList<NotificationEvents> notificationEvents = notificationEventsRepository.getAllNotificationEvents();

        for(NotificationEvents events : notificationEvents){
            EventItem eventItem = eventsRepository.getEvent((short)events.getEventId());
            scheduleAlarm(context, eventItem);
        }
    }

    private void scheduleAlarm(Context context, EventItem eventItem){
        String notificationText = String.format(context.getString(R.string.notificationText), eventItem.getTitle());

        long finalTime = timeUtils.getDateInMilliseconds(eventItem.getMeta().getDate(), timeUtils.getFourHoursLessTime(eventItem.getMeta().getTime()));

        Intent notificationIntent = new Intent(context, AlarmNotificationReceiver.class);
        notificationIntent.putExtra(AlarmNotificationReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra("content", notificationText);
        notificationIntent.putExtra("id", eventItem.getId());

        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, notificationIntent, PendingIntent.FLAG_CANCEL_CURRENT);

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP, finalTime, pendingIntent);
    }
}