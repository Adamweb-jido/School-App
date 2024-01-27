package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;

public class OnBoardingScreen extends AppCompatActivity {

    ViewPager2 onboardPager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_on_boarding_screen);
        onboardPager = findViewById(R.id.onBoardingViewPager);

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
        OnBoardingAdapter onBoardingAdapter = new OnBoardingAdapter(items);
        onboardPager.setAdapter(onBoardingAdapter);
    }
}