package com.example.newsapp;

import android.view.View;

public class businessFragment extends BaseFragment implements View.OnClickListener {

    public businessFragment() {
        super.setCategory("business");
        super.setTitle(R.string.business);
    }

}