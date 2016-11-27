package com.ayoprez.castro.common;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.ayoprez.castro.R;
import com.ayoprez.castro.model.models.EventItem;
import com.ayoprez.castro.model.models.NotificationEvents;
import com.ayoprez.castro.model.repository.EventsRepository;
import com.ayoprez.castro.model.repository.EventsRepositoryImpl;
import com.ayoprez.castro.model.repository.NotificationEventsRepository;
import com.ayoprez.castro.model.repository.NotificationEventsRepositoryImpl;

import java.util.ArrayList;

import javax.inject.Inject;

public class DeviceBootReceiver extends BroadcastReceiver {
    private static final String LOG_TAG = DeviceBootReceiver.class.getSimpleName();

    TimeUtils timeUtils = new TimeUtils();

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("android.intent.action.BOOT_COMPLETED")) {
           reSchedule(context);
        }else{
            Log.e("", "It is not a reboot intent");
        }
    }

    public void reSchedule(Context context){

        EventsRepository eventsRepository = new EventsRepositoryImpl();
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