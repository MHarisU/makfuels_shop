package com.makfuels.makfuels;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebResourceError;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebView webView = findViewById(R.id.webview);
        webView.clearCache(true);
        webView.clearHistory();
        WebSettings webSettings = webView.getSettings();
        webSettings.setJavaScriptEnabled(true);
        webView.loadUrl("http://www.makcom.cf/");

        LinearLayout backButton = findViewById(R.id.backButton);

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (webView.canGoBack()) {
                    webView.goBack();
                }
            }
        });

        LinearLayout homeButton = findViewById(R.id.homeButton);

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://www.makcom.cf/");

            }
        });




        CardView cardView = findViewById(R.id.cardView);
        ProgressBar progressBar = findViewById(R.id.progressBar);
        TextView tryButton = findViewById(R.id.tryButton);

        tryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                webView.loadUrl("http://www.makcom.cf/");

            }
        });

        webView.setWebViewClient(new WebViewClient() {

            @Override
            public void onReceivedError(WebView view, WebResourceRequest request, WebResourceError error) {
                // Handle error codes here
                if (error.getErrorCode() == ERROR_HOST_LOOKUP || error.getErrorCode() == ERROR_CONNECT) {
                    view.loadUrl("file:///android_asset/error.html");
                    tryButton.setVisibility(View.VISIBLE);

                } else if (error.getErrorCode() == 404) {
                    view.loadUrl("file:///android_asset/error.html");
                    tryButton.setVisibility(View.VISIBLE);

                }
                else{
                    view.loadUrl("file:///android_asset/down.html");
                    tryButton.setVisibility(View.VISIBLE);

                }
            }

            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                cardView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                cardView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                if (url.equals("file:///android_asset/error.html") || url.equals("file:///android_asset/down.html")){
                    tryButton.setVisibility(View.VISIBLE);

                }
                else {
                    tryButton.setVisibility(View.GONE);

                }
                Log.d("URL_CHECK", url);

            }


        });





    }
}