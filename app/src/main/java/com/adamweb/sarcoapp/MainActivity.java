package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.FirebaseApp;


public class MainActivity extends AppCompatActivity {


    ImageView rightSideAnim, leftSideAnim;
    TextView textAnim;

    Animation leftAnim, topAnim, textAnimation;
    SharedPreferences sharedPreferences;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        rightSideAnim = findViewById(R.id.imageView2);
        leftSideAnim = findViewById(R.id.imageView3);
        textAnim = findViewById(R.id.textView);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.slide_anim);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        rightSideAnim.setAnimation(topAnim);
        leftSideAnim.setAnimation(leftAnim);
        textAnim.setAnimation(textAnimation);
        new Handler().postDelayed(() -> {

            Intent intent = new Intent(getApplicationContext(),OnBoardingScreen.class);
            startActivity(intent);
            finish();
         /* sharedPreferences = getSharedPreferences("onBoard", MODE_PRIVATE);
            boolean isFirstTime = sharedPreferences.getBoolean("firstTime", true);

            if (isFirstTime){
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("firstTime", false);
                editor.apply();

                startActivity(new Intent(getApplicationContext(), OnBoardingScreen.class));
                finish();
            } else {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }*/
            },5000);
    }
}