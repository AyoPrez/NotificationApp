package com.ayoprez.castro.ui.fragments.aboutus;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ayoprez.castro.CastroApplication;
import com.ayoprez.castro.common.ErrorManager;
import com.ayoprez.castro.common.ErrorNotification;
import com.ayoprez.castro.common.ImageLib;
import com.ayoprez.castro.R;
import com.ayoprez.castro.presenter.aboutUs.AboutUsPresenter;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.Serializable;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 03.07.16.
 */
public class AboutUsFragment extends Fragment implements AboutUsView {

    @Inject
    ImageLib imageLib;

    @Inject
    AboutUsPresenter aboutUsPresenter;

    @Inject
    ErrorNotification errorNotification;

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
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            tvAboutUsDescription.setText(Html.fromHtml(description, Html.FROM_HTML_MODE_LEGACY).toString());
        } else {
            tvAboutUsDescription.setText(Html.fromHtml(description).toString());
        }
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
    public void openPhone(final String number) {
        try {
            Dexter.checkPermission(new PermissionListener() {
                @Override
                public void onPermissionGranted(PermissionGrantedResponse response) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + number));
                    startActivity(callIntent);
                }

                @Override
                public void onPermissionDenied(PermissionDeniedResponse response) {

                }

                @Override
                public void onPermissionRationaleShouldBeShown(PermissionRequest permission, PermissionToken token) {
                    token.continuePermissionRequest();
                }
            }, Manifest.permission.CALL_PHONE);
        }catch (Exception e){
            showErrorMessage(ErrorManager.ERROR);
        }

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
        sendIntent.putExtra(Intent.EXTRA_EMAIL, new String[]{mail});
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "");
        sendIntent.setType("message/rfc822");
        startActivity(sendIntent);
    }

    @Override
    public void showErrorMessage(byte errorMessage) {
        if (isAdded())
            errorNotification.showNotification(getContext(), getResources().getStringArray(R.array.errorsArray)[errorMessage]);
    }
}
