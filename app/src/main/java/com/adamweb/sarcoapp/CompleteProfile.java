package com.adamweb.sarcoapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;

import com.google.android.material.textfield.MaterialAutoCompleteTextView;

public class CompleteProfile extends AppCompatActivity {

    String [] combs = {"Computer/Biology", "Computer/Chemistry", "Computer/Physics", "Computer/Maths"};
    MaterialAutoCompleteTextView materialAutoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complete_profile);

        materialAutoCompleteTextView = findViewById(R.id.autoComplete);

        materialAutoCompleteTextView.setAdapter(new ArrayAdapter<String>(this, R.layout.comb_layout, combs));

    }
}