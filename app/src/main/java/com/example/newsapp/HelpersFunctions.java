package com.example.newsapp;

import android.content.Context;
import android.content.Intent;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class HelpersFunctions extends AppCompat {

    Context context;

    public HelpersFunctions(Context context) {
        this.context = context;
    }

    public void bottomNavigationFunction(BottomNavigationView bottomNavigationView){

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.saved_btm_item:
                    Intent intent = new Intent(this.context, SavedNews.class);
                    this.context.startActivity(intent);
                    break;

                case R.id.latest_btm_item:
                    intent = new Intent(this.context, MainActivity.class);
                    this.context.startActivity(intent);
                    break;

                case R.id.older_btm_item:
                    intent = new Intent(this.context, OlderNews.class);
                    this.context.startActivity(intent);
                    break;

                case R.id.search_btm_item:
                    intent = new Intent(this.context, SearchNews.class);
                    this.context.startActivity(intent);
                    break;
            }

            return true;
        });
    }

    public void clickOnOneItem(View v){

        TextView twTitle = v.findViewById(R.id.twTitle);
        TextView twAuthor = v.findViewById(R.id.twAuthor);
        TextView twPublished = v.findViewById(R.id.twPublished);
        TextView twImage = v.findViewById(R.id.twImage);
        TextView twLink = v.findViewById(R.id.twLink);

        String title = twTitle.getText().toString();
        String author = twAuthor.getText().toString();
        String published = twPublished.getText().toString();
        String image = twImage.getText().toString();
        String link = twLink.getText().toString();

        Intent intent = new Intent(this.context, OnePost.class);
        Bundle extras = new Bundle();

        extras.putString("title", title);
        extras.putString("author", author);
        extras.putString("published", published);
        extras.putString("image", image);
        extras.putString("link", link);

        intent.putExtras(extras);
        this.context.startActivity(intent);
    }
}
