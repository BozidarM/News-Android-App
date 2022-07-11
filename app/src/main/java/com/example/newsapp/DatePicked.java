package com.example.newsapp;

import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;


import static com.example.newsapp.showData.showOlderNews;

public class DatePicked extends AppCompat implements View.OnClickListener {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picked);
        setTitle(R.string.older_news);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.bottomNavigationFunction(bottomNavigationView);

        MenuItem menuItem = bottomNavigationView.getMenu().getItem(2);
        menuItem.setChecked(true);

        initNews();
    }

    @SuppressLint("HandlerLeak")
    private void initNews() {
        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        String keyword = extras.getString("keyword");

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainScrollView = findViewById(R.id.mainScrollView);

        TextView labelJson = findViewById(R.id.labelJson);
        labelJson.setText(R.string.loading_news);

        showOlderNews(mainScrollView, labelJson, this, inflater, date, keyword);
    }

    @Override
    public void onClick(View v) {
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.clickOnOneItem(v);
    }
}