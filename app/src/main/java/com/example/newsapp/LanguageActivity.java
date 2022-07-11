package com.example.newsapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;

public class LanguageActivity extends AppCompat {

    ImageButton eng, srb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        LocaleHelper lang = new LocaleHelper(this);

        eng = findViewById(R.id.eng_id);
        eng.setOnClickListener(view ->
        {
            lang.updateResource("en");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        srb = findViewById(R.id.srb_id);
        srb.setOnClickListener(view ->
        {
            lang.updateResource("sr");

            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });
    }
}