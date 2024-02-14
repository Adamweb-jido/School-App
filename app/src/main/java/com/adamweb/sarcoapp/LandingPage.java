package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.button.MaterialButton;

public class LandingPage extends AppCompatActivity {

    MaterialButton letsGo;
    Animation leftAnim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landing_page);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        letsGo = findViewById(R.id.goBtn);

        leftAnim = AnimationUtils.loadAnimation(this, R.anim.side_anim);
        letsGo.setAnimation(leftAnim);
        letsGo.setOnClickListener(v ->{
            startActivity(new Intent(getApplicationContext(), LoginActivity.class));
            finish();
            Animatoo.INSTANCE.animateSlideRight(this);
        });



    }
}