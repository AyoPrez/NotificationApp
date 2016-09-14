package com.ayoprez.castro.ui.fragments.events;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.models.EventItem;
import com.ayoprez.castro.presenter.adapters.events.EventAdapterPresenter;

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

        eventAdapterPresenter.setEventView(this, (EventItem)getArguments().getSerializable("event"));
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
                eventAdapterPresenter.notifyEvent();
            }
        });
    }

    @Override
    public void buttonShare() {
        bShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventAdapterPresenter.shareEventContent();
            }
        });
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        Toast.makeText(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage], Toast.LENGTH_LONG).show();
    }
}
