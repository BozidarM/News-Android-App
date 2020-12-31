package com.example.newsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class SearchNews extends AppCompatActivity implements View.OnClickListener {

    ConstraintLayout item;
    Button btnHome, btnSaved, btnOlder;
    LinearLayout mainScrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);

        initComponents();
    }

    private void initComponents() {
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        btnSaved = findViewById(R.id.btnSaved);
        btnSaved.setOnClickListener(this);
        btnOlder = findViewById(R.id.btnOlder);
        btnOlder.setOnClickListener(this);
    }


    @SuppressLint("HandlerLeak")
    private void initNews(String searchValue) {
        Intent intent = getIntent();
        String url = intent.getStringExtra("url");
        Api.getJSON(url, new ReadDataHandler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String answer = getJson();

                try {
                    JSONObject object = new JSONObject(answer);
                    JSONArray array = object.getJSONArray("news");
                    LinkedList<NewsModel> news = NewsModel.parseJSONArray(array);

                    mainScrollView = findViewById(R.id.mainScrollView);
                    LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    TextView labelJson = findViewById(R.id.labelJson);
                    labelJson.setText("");

                    for (NewsModel n : news) {
                        if(searchValue != null && !n.getTitle().toLowerCase().contains(searchValue.toLowerCase())){
                            continue;
                        }
                        item = (ConstraintLayout) inflater.inflate(R.layout.one_item, null);
                        item.setOnClickListener(SearchNews.this);

                        TextView twTitle = item.findViewById(R.id.twTitle);
                        twTitle.setText(n.getTitle());
                        TextView twAuthor = item.findViewById(R.id.twAuthor);
                        twAuthor.setText(n.getAuthor());
                        TextView twPublished = item.findViewById(R.id.twPublished);
                        String date = n.getPublishedAt();
                        String ymd = date.substring(0,16);
                        twPublished.setText(ymd);
                        TextView twLink = item.findViewById(R.id.twLink);
                        twLink.setText(n.getSourceLink());
                        TextView twImage = item.findViewById(R.id.twImage);
                        twImage.setText(n.getImage());

                        ImageView image = item.findViewById(R.id.imageThumb);
                        if (n.getImage() == ""){
                            Picasso.get().load("").resize(136, 136).into(image);
                        }else {
                            Picasso.get().load(n.getImage()).resize(136, 136).into(image);
                        }

                        mainScrollView.addView(item);
                        labelJson.setVisibility(View.GONE);
                    }

                } catch (Exception e) {

                }
            }
        });
        ((TextView)findViewById(R.id.labelJson)).setText("Loading...");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.search, menu);

        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuSearch.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();

                initNews(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.btnHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSaved:
                intent = new Intent(this, SavedNews.class);
                startActivity(intent);
                break;

            case R.id.btnOlder:
                intent = new Intent(this, OlderNews.class);
                startActivity(intent);
                break;

            case R.id.clOne:
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

                intent = new Intent(this, OnePost.class);
                Bundle extras = new Bundle();

                extras.putString("title", title);
                extras.putString("author", author);
                extras.putString("published", published);
                extras.putString("image", image);
                extras.putString("link", link);

                intent.putExtras(extras);
                startActivity(intent);
                break;
        }
    }
}