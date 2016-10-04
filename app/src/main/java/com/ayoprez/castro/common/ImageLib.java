package com.ayoprez.castro.common;

import android.graphics.Bitmap;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.io.IOException;

/**
 * Created by ayo on 12.09.16.
 */
public class ImageLib {

    public void setImageIntoView(String urlImage, ImageView imageView){
        Picasso.with(imageView.getContext()).load(urlImage).fit().into(imageView);
    }

    public void setImageIntoViewWithPlaceHolder(String urlImage, ImageView imageView, int placeholder){
        Picasso.with(imageView.getContext()).load(urlImage).placeholder(placeholder).fit().into(imageView);
    }
}
