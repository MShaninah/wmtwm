package com.example.wmt_wms_s23;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;


public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Delay for 2 seconds and then navigate to the login activity
        new Handler(Looper.getMainLooper()).postDelayed(() -> {
            Intent intent = new Intent(HomeActivity.this, LoginActivity.class);
            startActivity(intent);
            finish(); // Finish the SplashActivity so the user can't navigate back.
        }, 2000); // Delay in milliseconds (2 seconds in this case)
    }
}