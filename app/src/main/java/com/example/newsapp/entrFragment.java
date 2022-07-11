package com.example.newsapp;

import android.view.View;

public class entrFragment extends BaseFragment implements View.OnClickListener {

    public entrFragment() {
        super.setCategory("entertainment");
        super.setTitle(R.string.entertainment);
    }

}