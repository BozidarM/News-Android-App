package com.example.newsapp;

import android.view.View;

public class techFragment extends BaseFragment implements View.OnClickListener {

    public techFragment() {
        super.setCategory("technology");
        super.setTitle(R.string.technology);
    }
}