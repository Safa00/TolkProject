package com.example.safaa.tolkappen;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;


import java.util.ArrayList;


public class WebpageFragment extends Fragment {
    MainActivity main = new MainActivity();

    private static WebView browser;
    private static Button urlButton;

    private ArrayList<Pages> pageListArabic = new ArrayList<>();
    private ArrayList<Pages> pageListSwedish = new ArrayList<>();
    private ArrayList<String> myLinksArabic = new ArrayList<>();
    private ArrayList<String> myLinksSwedish = new ArrayList<>();

    private boolean arabic = false;

    private int counter = 0;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        pageListArabic = main.getPagesArabic();
        pageListSwedish = main.getPagesSwedish();



        for(int i = 0; i < pageListArabic.size(); i=i+2){
            myLinksArabic.add(pageListArabic.get(i).toString());
        }
        for(int i = 0; i < pageListSwedish.size(); i=i+2){
            myLinksSwedish.add(pageListSwedish.get(i).toString());
        }

        for(int i = 0; i < myLinksSwedish.size(); i++){
            System.out.println(myLinksSwedish.get(i));
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v =  inflater.inflate(R.layout.fragment_import, container, false);

        return v;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        urlButton = (Button)view.findViewById(R.id.button);
        browser = view.findViewById(R.id.webView);
        browser.getSettings().setLoadsImagesAutomatically(true);
        browser.getSettings().setJavaScriptEnabled(true);
        browser.setScrollBarStyle(View.SCROLLBARS_INSIDE_OVERLAY);
        browser.loadUrl("https://docs.google.com/gview?embedded=true&url=" + myLinksArabic.get(counter));
        urlButton.setOnClickListener(
                new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        counter++;
                        counter = counter % myLinksArabic.size();
                        browser.loadUrl("https://docs.google.com/gview?embedded=true&url=" + myLinksArabic.get(counter));
                    }
                }
        );


/*

        if(arabic==false) {
            browser.loadUrl("https://docs.google.com/gview?embedded=true&url=" + myLinksSwedish.get(0));
        }else{
            browser.loadUrl("https://docs.google.com/gview?embedded=true&url=" + myLinksArabic.get(0));
        }
*/
        //////////////////////////////


    }



}

