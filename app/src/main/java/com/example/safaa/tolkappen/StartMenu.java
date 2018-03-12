package com.example.safaa.tolkappen;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;

/**
 * Created by safaa on 2018-02-02.
 */

public class StartMenu extends Activity {


    private static Button urlButton;
    private static WebView browser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_menu);

        openUrl();
    }

    public void openUrl(){
        urlButton = (Button)findViewById(R.id.button);
        browser = (WebView)findViewById(R.id.webView);
        //final WebSettings webSettings = browser.getSettings();
     //   WebView webview = (WebView) findViewById(R.id.webview);
        browser.getSettings().setJavaScriptEnabled(true);



        urlButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        String pdf = "http://128.199.46.182/arabisk/SKL/ArabiskaSamtyckesblankett.pdf";
                        browser.loadUrl("http://drive.google.com/viewerng/viewer?embedded=true&url=" + pdf);
                        //String url = url_text.getText().toString();
                       // String url = "http://128.199.46.182/arabisk/CSN/0/0.html";
                        //browser.getSettings().setLoadsImagesAutomatically(true);
                     //   webSettings.setJavaScriptEnabled(true);
                        //browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
                        //browser.loadUrl(url);
                    }
                }
        );
    }




}
