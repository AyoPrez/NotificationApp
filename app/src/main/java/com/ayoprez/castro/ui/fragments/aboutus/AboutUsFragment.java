package com.ayoprez.castro.ui.fragments.aboutus;

import android.content.Intent;
import android.net.Uri;
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
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenter;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsFragment extends Fragment implements AboutUsView{

    @Inject
    ImageLib imageLib;

    @Inject
    AboutUsPresenter aboutUsPresenter;

    @BindView(R.id.tv_name_aboutus)
    TextView tvAboutUsName;
    @BindView(R.id.tv_description_aboutus)
    TextView tvAboutUsDescription;
    @BindView(R.id.iv_aboutus)
    ImageView ivAboutUsImage;
    @BindView(R.id.button_call_aboutus)
    ImageButton btnAboutUsCall;
    @BindView(R.id.button_share_aboutus)
    ImageButton btnAboutUsShare;
    @BindView(R.id.button_mail_aboutus)
    ImageButton btnAboutUsMail;

    private static AboutUsFragment instance;

    public static AboutUsFragment getInstance() {
        if(instance!=null){
            return instance;
        }else{
            return new AboutUsFragment();
        }
    }

    public AboutUsFragment(){}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((CastroApplication)getActivity().getApplication()).getComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
        ButterKnife.bind(this, view);
        aboutUsPresenter.setView(this);
        return view;
    }

    @Override
    public void displayName(String name) {
        tvAboutUsName.setText(name);
    }

    @Override
    public void displayDescription(String description) {
        tvAboutUsDescription.setText(description);
    }

    @Override
    public void displayImage(String image) {
        imageLib.setImageIntoView(image, ivAboutUsImage);
    }

    @Override
    public void clickShareButton() {
        btnAboutUsShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsPresenter.openShare();
            }
        });
    }

    @Override
    public void openShare(String shareText) {
        Intent shareIntent = new Intent();
        shareIntent.setAction(Intent.ACTION_SEND);
        shareIntent.putExtra(Intent.EXTRA_TEXT, shareText);
        shareIntent.setType("text/plain");
        startActivity(shareIntent);
    }

    @Override
    public void clickCallButton() {
        btnAboutUsCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsPresenter.openCall();
            }
        });
    }

    @Override
    public void openPhone(String number) {
        Intent callIntent = new Intent(Intent.ACTION_CALL);
        callIntent.setData(Uri.parse("tel:" + number));
        startActivity(callIntent);
    }

    @Override
    public void clickMailButton() {
        btnAboutUsMail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                aboutUsPresenter.openMail();
            }
        });
    }

    @Override
    public void openMail(String mail) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_EMAIL,mail);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        sendIntent.setType("message/rfc822");
        startActivity(sendIntent);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        Toast.makeText(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage], Toast.LENGTH_LONG).show();
    }
}
