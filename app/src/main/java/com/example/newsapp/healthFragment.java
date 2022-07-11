package com.example.newsapp;

import android.view.View;

public class healthFragment extends BaseFragment implements View.OnClickListener {

    public healthFragment() {
        super.setCategory("health");
        super.setTitle(R.string.health);
    }
}