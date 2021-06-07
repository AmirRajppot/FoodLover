package com.example.foodlover.Activites;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.Toast;

import com.example.foodlover.R;

public class Splash extends AppCompatActivity {
    SharedPreferences preferences;
    String user_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        preferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
        user_id = preferences.getString("id", "");
        Thread mThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2500);
                    if (user_id.matches("")) {
                        Intent mainIntent = new Intent(Splash.this, Login.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();

                    } else {
                        Intent mainIntent = new Intent(Splash.this, Home.class);
                        Splash.this.startActivity(mainIntent);
                        Splash.this.finish();
                    }
                } catch (InterruptedException e) {
                    Toast.makeText(Splash.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        };
        mThread.start();
    }

}
