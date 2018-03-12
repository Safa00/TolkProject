package com.example.safaa.tolkappen;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static ArrayList<Pages> pagesArabic = new ArrayList<>();
    public static ArrayList<Pages> pagesSwedish = new ArrayList<>();

    public static ArrayList<Pages> getPagesArabic() {
        return pagesArabic;
    }
    public static ArrayList<Pages> getPagesSwedish() {
        return pagesSwedish;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ser till att internt minne raderas om det finns data på
        PrintWriter writer = null;
        File f = new File("myText.txt");
        if(f.exists() && !f.isDirectory()) {
            try {
                writer = new PrintWriter("myText.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            writer.print("");
            writer.close();
        }

        //Hämtar ny data från wiki
        WikiDataTaskArabic mGetLinksTaskA = new WikiDataTaskArabic(this);
        mGetLinksTaskA.execute();
        WikiDataTaskSwedish mGetLinksTaskS = new WikiDataTaskSwedish(this);
        mGetLinksTaskS.execute();

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {

        Fragment fragment = null;
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            fragment = new WebpageFragment();
        } else if (id == R.id.facebook_contact) {
            fragment = new FacebookFragment();
        } else if (id == R.id.nav_slideshow) {
            fragment = new ContactFragment();
        }

        if(fragment != null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            fragmentTransaction.replace(R.id.screen_area, fragment);

            fragmentTransaction.commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    //Denna metod hämtar sparade länkar från array
    public void updateAdapterArabic(ArrayList<String> links) {

        for(int i = 0; i < links.size(); i++){
            pagesArabic.add(new Pages(links.get(i)));
        }
    }

    //Denna metod hämtar sparade länkar från array
    public void updateAdapterSwedish(ArrayList<String> links) {

        for(int i = 0; i < links.size(); i++){
            pagesSwedish.add(new Pages(links.get(i)));
        }
    }


}
