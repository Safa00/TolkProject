package com.example.safaa.tolkappen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main );
    }

    public void onBlankettButtonClick(View view ){

        if(view.getId() == R.id.buttonBlankett){
            Intent i = new Intent(MainActivity.this , StartMenu.class) ;
            startActivity(i);
        }
    }
    public void onContactButtonClick (View view ){

        if(view.getId() == R.id.buttonContact){
            Intent i = new Intent(MainActivity.this , ContactPage.class) ;
            startActivity(i);
        }
    }

}
