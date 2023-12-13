package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {


    ImageView rightSideAnim, leftSideAnim;
    TextView textAnim;

    Animation leftAnim, topAnim, textAnimation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rightSideAnim = findViewById(R.id.imageView2);
        leftSideAnim = findViewById(R.id.imageView3);
        textAnim = findViewById(R.id.textView);

        topAnim = AnimationUtils.loadAnimation(this, R.anim.slide_anim);
        leftAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        textAnimation = AnimationUtils.loadAnimation(this, R.anim.text_anim);

        rightSideAnim.setAnimation(topAnim);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                finish();
            }
        },5000);
    }
}