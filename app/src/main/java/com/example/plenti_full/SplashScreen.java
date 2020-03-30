package com.example.plenti_full;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Using this EasySplashScreen we can easily create a custom splash screen! We implemented this from a free github repository. Link in Read me!
        EasySplashScreen config = new EasySplashScreen(SplashScreen.this)
                .withFullScreen()
                .withTargetActivity(MainActivity.class)
                .withSplashTimeOut(3000)
                .withBackgroundColor(Color.parseColor("#C09074"))
                //Splash image created using www.freelogodesign.org link also in Read Me.
                .withLogo(R.drawable.splashimage);

        ActionBar actionBar = getSupportActionBar();
        //Hide action bar at the top of the screen to keep it looking clean!
        actionBar.hide();
        View easySplashScreen = config.create();
        setContentView(easySplashScreen);
    }
}
