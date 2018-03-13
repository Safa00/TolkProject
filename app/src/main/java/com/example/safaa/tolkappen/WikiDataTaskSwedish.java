package com.example.safaa.tolkappen;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WikiDataTaskSwedish extends AsyncTask<String, String, String> {
    private MainActivity mImportFragment;
    private ArrayList<String> mLinks = new ArrayList<String>();

    public WikiDataTaskSwedish(MainActivity importFragment){
        this.mImportFragment = importFragment;
    }


    private static final Pattern urlPattern = Pattern.compile(
            "(?:^|[\\W])((ht|f)tp(s?):\\/\\/|www\\.)"
                    + "(([\\w\\-]+\\.){1,}?([\\w\\-.~]+\\/?)*"
                    + "[\\p{Alnum}.,%_=?&#\\-+()\\[\\]\\*$~@!:/{};']*)",
            Pattern.CASE_INSENSITIVE | Pattern.MULTILINE | Pattern.DOTALL);

    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection connection = null;

        String line;
        String text;
        Matcher matcher;
        BufferedReader br = null;
        try{
            URL url = new URL("http://128.199.46.182/wiki/dokuwiki-2017-02-19e/doku.php?id=information:svensk:filer");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            InputStream stream = connection.getInputStream();

            br = new BufferedReader(new InputStreamReader(stream));

            StringBuilder sb = new StringBuilder();

            line = br.readLine();
            while (line != null) {
                //System.out.println(line);
                if ((line.contains("href=\"http://")) && (!line.contains("gnu")) && (!line.contains("dokuwiki"))
                        && (!line.contains("w3") && (!line.contains("php")))) {
                    text = line;
                    matcher = urlPattern.matcher(text);
                    while (matcher.find()) {
                        int matchStart = matcher.start(1);
                        int matchEnd = matcher.end();
                        this.mLinks.add(text.substring(matchStart, matchEnd));
                     //   System.out.println(roar);
                    }
                }

                //Om det är en sträng med rubriksättning h2...

                line = br.readLine();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if(connection != null){
                connection.disconnect();
            }
            try{
                if(br != null){
                    br.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        mImportFragment.updateAdapterSwedish(mLinks);
    }


}
