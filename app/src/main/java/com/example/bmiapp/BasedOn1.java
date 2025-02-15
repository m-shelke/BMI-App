package com.example.bmiapp;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.material.progressindicator.BaseProgressIndicatorSpec;

import java.util.Objects;

public class BasedOn1 extends AppCompatActivity {

//    Declaring .xml View in Variable
    WebView webView;
    ProgressBar pgBAr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_based_on1);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

//        hiding ActionBar
        Objects.requireNonNull(getSupportActionBar()).hide();

//        finding .xml View id's
         webView = findViewById(R.id.webview);
         pgBAr = findViewById(R.id.pgBar);

//        loading url in webView
        webView.loadUrl("https://my.clevelandclinic.org/health/articles/9464-body-mass-index-bmi");

//        setting webView client for loading and managing multiple url link in the stact
        webView.setWebViewClient(new WebViewClient(){


//            loading new webView and multiple webView request on same webView by using this method
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return super.shouldOverrideUrlLoading(view, request);
            }

//            onPageStarted showing ProgressBar
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                pgBAr.setVisibility(View.VISIBLE);
                super.onPageStarted(view, url, favicon);
            }

//            Dis appearing ProgressBar onPageFinished method
            @Override
            public void onPageFinished(WebView view, String url) {
                pgBAr.setVisibility(View.GONE);
                super.onPageFinished(view, url);
            }
        });

    }

//    What should happened, if user back press from his device
    @Override
    public void onBackPressed() {
//         if the webView is can go back, then go back on Back pressed
        if (webView.canGoBack()){
//            WebView can go back, then go back
            webView.goBack();
        }else {
//            if not, then back press super Activity
            super.onBackPressed();
        }
    }
}