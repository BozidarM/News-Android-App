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
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class DatePicked extends AppCompatActivity implements View.OnClickListener {

    Button btnHome, btnSaved;
    ConstraintLayout item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_date_picked);

        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        btnSaved = findViewById(R.id.btnSaved);
        btnSaved.setOnClickListener(this);

        initNews();
    }

    @SuppressLint("HandlerLeak")
    private void initNews() {
        Bundle extras = getIntent().getExtras();
        String date = extras.getString("date");
        String url = "https://api.currentsapi.services/v1/search?language=en&end_date="+date+"&apiKey=dSIN6N3NZn8yXerCVobWMBcRpoSpzfrLqGiETykRU1MWq4Su";
        Api.getJSON(url, new ReadDataHandler(){
            @Override
            public void handleMessage(@NonNull Message msg) {
                String answer = getJson();

                try {
                    JSONObject object = new JSONObject(answer);
                    JSONArray array = object.getJSONArray("news");
                    LinkedList<NewsModel> news = NewsModel.parseJSONArray(array);

                    LinearLayout mainScrollView = findViewById(R.id.mainScrollView);
                    LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

                    TextView labelJson = findViewById(R.id.labelJson);
                    labelJson.setText("");

                    for (NewsModel n : news) {
                        item = (ConstraintLayout) inflater.inflate(R.layout.one_item, null);
                        item.setOnClickListener(DatePicked.this);

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
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnHome:
                Intent intent = new Intent(this, MainActivity.class);
                startActivity(intent);
                break;

            case R.id.btnSaved:
                intent = new Intent(this, SavedNews.class);
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