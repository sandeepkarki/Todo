package com.example.todo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import com.example.todo.Login.Login;
import com.example.todo.Utilities.SharedPreference;

public class SplashScreen extends AppCompatActivity {

    // Splash screen timer

    private static int SPLASH_TIME_OUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();
        goToNext();

    }

    private void goToNext() {

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                if (!SharedPreference.getInstance(SplashScreen.this).getString("UserId", "").equalsIgnoreCase("")) {

                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                    finish();

                } else {

                    Intent intent = new Intent(SplashScreen.this, Login.class);
                    startActivity(intent);
                    finish();
                }


            }
        }, SPLASH_TIME_OUT);
    }
}
