package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class OnePost extends AppCompatActivity implements View.OnClickListener {

    String title, author, published, image, link;
    Button btnSave, btnHome, btnSaved;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_one_post);

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
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        btnSaved = findViewById(R.id.btnSaved);
        btnSaved.setOnClickListener(this);

        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        author = extras.getString("author");
        published = extras.getString("published");
        image = extras.getString("image");
        link = extras.getString("link");
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnSave:

                DataBase db = new DataBase(this);
                List<NewsModelDB> post = db.getAllSavedNews();
                ArrayList<String> titles = new ArrayList<>();
                for (NewsModelDB n : post) {
                    String titleFromDb = n.getTitle();
                    titles.add(titleFromDb);
                }

                if (titles.contains(title)){
                    Toast.makeText(this, "This post is already saved.", Toast.LENGTH_SHORT).show();
                }else{
                    db.addPost(title, author, published, image, link);
                    Toast.makeText(this, "Post is saved.", Toast.LENGTH_SHORT).show();
                }
                break;

            case R.id.btnSaved:
                Intent intent = new Intent(this, SavedNews.class);
                startActivity(intent);
                break;

            case R.id.btnHome:
                intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;
        }
    }
}