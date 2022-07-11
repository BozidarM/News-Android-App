package com.example.newsapp;

import android.view.View;

public class scienceFragment extends BaseFragment implements View.OnClickListener {

    public scienceFragment() {
        super.setCategory("science");
        super.setTitle(R.string.science);
    }
}