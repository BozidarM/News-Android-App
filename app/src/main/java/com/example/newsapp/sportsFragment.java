package com.example.newsapp;

import android.view.View;

public class sportsFragment extends BaseFragment implements View.OnClickListener {

    public sportsFragment() {
        super.setCategory("sports");
        super.setTitle(R.string.sports);
    }
}