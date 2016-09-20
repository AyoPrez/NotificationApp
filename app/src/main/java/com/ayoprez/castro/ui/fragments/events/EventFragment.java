package com.ayoprez.castro.ui.fragments.events;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.ShareCompat;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.AlarmNotificationReceiver;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;

import java.util.HashMap;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 10.09.16.
 */
public class EventFragment extends Fragment implements EventView {

    @Inject
    ImageLib imageLib;

    @Inject
    EventAdapterPresenter eventAdapterPresenter;

    @Inject
    ErrorNotification errorNotification;

//    @Inject
//    TimeUtils timeUtils;

    @BindView(R.id.image_event)
    protected ImageView iVEvent;
    @BindView(R.id.tv_title_event)
    protected TextView tvSubtitle;
    @BindView(R.id.tv_subtitle_event)
    protected TextView tvTitle;
    @BindView(R.id.tv_date_event)
    protected TextView tvDate;
    @BindView(R.id.tv_time_event)
    protected TextView tvTime;
    @BindView(R.id.tv_price_event)
    protected TextView tvPrice;
    @BindView(R.id.button_notification_event)
    protected ImageButton bNotification;
    @BindView(R.id.button_share_event)
    protected ImageButton bShare;
    @BindView(R.id.tv_description_event)
    protected TextView tvDescription;

    protected short eventId;

    public EventFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_event, container, false);
        ButterKnife.bind(this, view);

        eventId = getArguments().getShort("eventId");

        eventAdapterPresenter.setEventView(this, eventId);

        return view;
    }

    @Override
    public void displayTitle(String title) {
        tvTitle.setText(title);
    }

    @Override
    public void displaySubtitle(String subtitle) {
        tvSubtitle.setText(subtitle);
    }

    @Override
    public void displayTime(String time) {
        tvTime.setText(time);
    }

    @Override
    public void displayDate(String date) {
        tvDate.setText(date);
    }

    @Override
    public void displayDescription(String description) {
        tvDescription.setText(description);
    }

    @Override
    public void displayImage(String image) {
        imageLib.setImageIntoView(image, iVEvent);
    }

    @Override
    public void displayPrice(String price) {
        tvPrice.setText(price);
    }

    @Override
    public void buttonNotify() {
        bNotification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventAdapterPresenter.notifyEvent(eventId);
            }
        });
    }

    @Override
    public void buttonShare() {
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventAdapterPresenter.shareEventContent(eventId);
            }
        });
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        errorNotification.showNotification(getView(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }

    @Override
    public void shareContent(HashMap<String, String> eventItemData){
        String shareText = String.format(getString(R.string.shareText),
                eventItemData.get("DATE"),
                eventItemData.get("TIME"),
                eventItemData.get("TITLE"),
                eventItemData.get("PRICE"));

        Intent shareIntent = ShareCompat.IntentBuilder.from(getActivity())
                .setType("text/plain")
                .setText(shareText)
                .getIntent();
        if (shareIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(shareIntent);
        }
    }
    @Override
    public void notifyAlarmEvent(long time, String eventTitle){
        String notificationText = String.format(getString(R.string.notificationText), eventTitle);

        Notification notification = getNotificationLogic(notificationText);
        alarmManagerLogic(notification, time);
    }

    protected Notification getNotificationLogic(String content){

        String action = "EVENT";

        // Create a pending intent to open the the application when the notification is clicked.
        //Restart the app.
        Intent launchIntent = getActivity().getPackageManager().getLaunchIntentForPackage(getActivity().getPackageName());

        Bundle bundle = new Bundle();
        bundle.putShort("eventId", eventId);

        if(launchIntent != null){
            launchIntent.setAction(action);
            launchIntent.putExtras(bundle);
        }

        PendingIntent pendingIntent = PendingIntent.getActivity(getActivity(), 0, launchIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification.Builder builder = new Notification.Builder(getActivity());
        builder.setContentTitle(getString(R.string.app_name));
        builder.setContentText(content);
        builder.setSmallIcon(R.mipmap.ic_launcher);
        builder.setContentIntent(pendingIntent);

        return builder.build();
    }

    protected void alarmManagerLogic(Notification notification, long time){
        Intent notificationIntent = new Intent(getActivity(), AlarmNotificationReceiver.class);
        notificationIntent.putExtra(AlarmNotificationReceiver.NOTIFICATION_ID, 1);
        notificationIntent.putExtra(AlarmNotificationReceiver.NOTIFICATION, notification);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getActivity(), 0, notificationIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        AlarmManager alarmManager = (AlarmManager) getActivity().getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.ELAPSED_REALTIME_WAKEUP, time, pendingIntent);
    }
}
