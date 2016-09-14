package com.ayoprez.castro.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import com.ayoprez.castro.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by ayo on 10.09.16.
 */
public class WebViewFragment extends Fragment {

    @BindView(R.id.webView)
    protected WebView webView;
    @BindView(R.id.pb_web)
    protected ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_webview, container, false);
        ButterKnife.bind(this, v);

        loadWeb(getArguments().getString("url"));

        return v;
    }

    private void loadWeb(String url){
        webView.loadUrl(url);

        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        webView.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageFinished(WebView view, String url) {
                progressBar.setVisibility(View.GONE);
            }
        });

        webView.setWebChromeClient(new WebChromeClient() {
            public void onProgressChanged(WebView view, int progress) {
                progressBar.setProgress(progress * 100);
            }
        });
    }
}
