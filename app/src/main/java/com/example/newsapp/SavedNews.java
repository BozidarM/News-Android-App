package com.example.newsapp;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SavedNews extends AppCompat implements View.OnClickListener {

    CardView item;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_news);
        setTitle(R.string.saved_news);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.bottomNavigationFunction(bottomNavigationView);

        MenuItem menuItem = bottomNavigationView.getMenu().getItem(1);
        menuItem.setChecked(true);

        initComponents();
    }

    private void initComponents() {

        DataBase db = new DataBase(this);
        List<NewsModelDB> post = db.getAllSavedNews();

        LinearLayout mainScrollView = findViewById(R.id.mainScrollView);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView labelJson = findViewById(R.id.labelJson);
        labelJson.setText("");

        for (NewsModelDB n : post) {
            item = (CardView) inflater.inflate(R.layout.one_item, null);
            item.setOnClickListener(this);

            int poId = n.getPostId();
            TextView twTitle = item.findViewById(R.id.twTitle);
            twTitle.setText(n.getTitle());
            TextView twAuthor = item.findViewById(R.id.twAuthor);
            twAuthor.setText(n.getAuthor());
            TextView twPublished = item.findViewById(R.id.twPublished);
            String date = n.getPublished_at();
            String ymd = date.substring(0, 16);
            twPublished.setText(ymd);
            TextView twLink = item.findViewById(R.id.twLink);
            twLink.setText(n.getLink());
            TextView twImage = item.findViewById(R.id.twImage);
            twImage.setText(n.getImage());

            ImageView image = item.findViewById(R.id.imageThumb);
            if (n.getImage() == null) {
                Picasso.get().load("https://kwx2f3rgme1bvul52zzobow3-wpengine.netdna-ssl.com/wp-content/themes/realestate-7/images/no-image.png").resize(150, 136).into(image);
            } else {
                String img_url = n.getImage();
                if (!(img_url.contains("https") || img_url.contains("http"))) {
                    img_url = "https:" + img_url;
                }
                Picasso.get().load(img_url).resize(150, 136).into(image);
            }
            Button btnRemove = new Button(this);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
            );
            btnRemove.setLayoutParams(params);
            btnRemove.setBackgroundResource(R.color.accent_color);
            btnRemove.setTextColor(getResources().getColor(R.color.primary_text));
            btnRemove.setTextSize(10);
            btnRemove.setText(R.string.button_delete);
            btnRemove.setId(poId);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deletePost(poId);
                    btnRemove.setEnabled(false);
                    btnRemove.setText(R.string.button_deleted);
                    Toast.makeText(SavedNews.this, R.string.msg_deleted, Toast.LENGTH_SHORT).show();
                }
            });
            item.addView(btnRemove);

            mainScrollView.addView(item);
            labelJson.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.clickOnOneItem(v);
    }
}