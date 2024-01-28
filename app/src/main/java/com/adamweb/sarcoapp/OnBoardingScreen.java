package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingScreen extends AppCompatActivity {

    ViewPager2 onboardPager;
    LinearLayout indicator;
    OnBoardingAdapter onBoardingAdapter;
    MaterialButton skipBtn, doneBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        onboardPager = findViewById(R.id.onBoardingViewPager);
        indicator = findViewById(R.id.dotIndicators);
        skipBtn = findViewById(R.id.skipBtn);
        doneBtn = findViewById(R.id.doneBtn);

        skipBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),LandingPage.class);
                startActivity(intent);
                finish();
            }
        });

        List<OnBoardingItem> items = new ArrayList<>();
        items.add(new OnBoardingItem("Your Album is Now Digital",
                "Are you tired of paying your money to get copy of your manual photo Album?, yes! we have got you covered, Sarco Pixel Allows you to access your photo Album digitally on your smartphone.",
                R.drawable.album_onboard));
        items.add(new OnBoardingItem("Chat With your Mates Easily",
                "Do you know by using sarco pixel you can chat with your mates,? moreover you can change your name, picture and even your phone number, by simply clicking on edit profile, you can do more.",
                R.drawable.chat_onboard));
        items.add(new OnBoardingItem("Get Connected everywhere",
                "I lost my Album Copy, and am far away from my state, how can i see my classmates\" -Asked by a Student. We have solved this problem, just download sarco pixel on app store to get connected with your mates everywhere you go.",
                R.drawable.connet_onboard
                ));
        onBoardingAdapter = new OnBoardingAdapter(items);
        onboardPager.setAdapter(onBoardingAdapter);

        onBoardIndicator();
        onboardCurrentIndicator(0);
        onboardPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                onboardCurrentIndicator(position);
            }
        });

    }

   public void onBoardIndicator(){
       ImageView [] indicators = new ImageView[onBoardingAdapter.getItemCount()];
       LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
       layoutParams.setMargins(10, 0, 10, 0);
       for (int i = 0; i < indicators.length; i++){
           indicators[i] = new ImageView(getApplicationContext());
           indicators[i].setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
           indicators[i].setLayoutParams(layoutParams);
           indicator.addView(indicators[i]);
       }
   }

   public void onboardCurrentIndicator(int index){
        int indicatorCount = indicator.getChildCount();
        for (int i = 0; i < indicatorCount; i++){
            ImageView imageView = (ImageView)indicator.getChildAt(i);
            if (i == index){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_active));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(), R.drawable.indicator_inactive));
            }
        }

        if (index == 2){
            doneBtn.setVisibility(View.VISIBLE);
            doneBtn.setText("Done");
            doneBtn.setAllCaps(false);
        }

   }

   private void skipBtn(){

   }
}