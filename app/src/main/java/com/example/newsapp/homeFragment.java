package com.example.newsapp;

import android.view.View;


public class homeFragment extends BaseFragment implements View.OnClickListener {

    public homeFragment() {
        super.setCategory("general");
        super.setTitle(R.string.app_name);
    }

}