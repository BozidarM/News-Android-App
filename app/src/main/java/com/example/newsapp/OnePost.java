package com.example.newsapp;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

public class OnePost extends AppCompat implements View.OnClickListener {

    String title, author, published, image, link;
    Button btnSave;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_post);
        setTitle(R.string.app_name);

        initComponents();

        WebView browser = findViewById(R.id.myWebView);

        WebSettings webSettings = browser.getSettings();
        webSettings.setJavaScriptEnabled(true);
        if (Build.VERSION.SDK_INT < 18) {
            browser.getSettings().setRenderPriority(WebSettings.RenderPriority.HIGH);
        }
        browser.setWebViewClient(new WebViewClient());
        browser.loadUrl(link);
    }

    private void initComponents() {
        btnSave = findViewById(R.id.btnSave);
        btnSave.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        author = extras.getString("author");
        published = extras.getString("published");
        image = extras.getString("image");
        link = extras.getString("link");

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.bottomNavigationFunction(bottomNavigationView);
    }

    @Override
    public void onClick(View v) {

        DataBase db = new DataBase(this);
        List<NewsModelDB> post = db.getAllSavedNews();
        ArrayList<String> titles = new ArrayList<>();
        for (NewsModelDB n : post) {
            String titleFromDb = n.getTitle();
            titles.add(titleFromDb);
        }

        if (titles.contains(title)){
            Toast.makeText(this, R.string.already_saved, Toast.LENGTH_SHORT).show();
        }else{
            db.addPost(title, author, published, image, link);
            Toast.makeText(this, R.string.post_saved, Toast.LENGTH_SHORT).show();
        }
    }
}