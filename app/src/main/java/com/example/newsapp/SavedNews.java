package com.example.newsapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SavedNews extends AppCompatActivity implements View.OnClickListener {

    Button btnHome, btnOlder;
    ConstraintLayout item;
    TextView twId;
    ArrayList<String> postids = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_saved_news);

        initComponents();
    }

    private void initComponents() {
        btnHome = findViewById(R.id.btnHome);
        btnHome.setOnClickListener(this);
        btnOlder = findViewById(R.id.btnOlder);
        btnOlder.setOnClickListener(this);


        DataBase db = new DataBase(this);
        List<NewsModelDB> post = db.getAllSavedNews();

        LinearLayout mainScrollView = findViewById(R.id.mainScrollView);
        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        TextView labelJson = findViewById(R.id.labelJson);
        labelJson.setText("");

        for (NewsModelDB n : post) {
            item = (ConstraintLayout) inflater.inflate(R.layout.one_item, null);
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
            if (n.getImage() == "") {
                Picasso.get().load("").resize(136, 136).into(image);
            } else {
                Picasso.get().load(n.getImage()).resize(136, 136).into(image);
            }

            Button btnRemove = new Button(this);
            ConstraintLayout.LayoutParams params = new ConstraintLayout.LayoutParams(
                    160,
                    65
            );
            btnRemove.setLayoutParams(params);
            btnRemove.setBackgroundResource(R.color.accent_color);
            btnRemove.setTextColor(getResources().getColor(R.color.primary_text));
            btnRemove.setTextSize(10);
            btnRemove.setText("Remove");
            btnRemove.setId(poId);
            btnRemove.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    db.deletePost(poId);
                    btnRemove.setEnabled(false);
                    btnRemove.setText("Removed");
                    Toast.makeText(SavedNews.this, "Post is removed.", Toast.LENGTH_SHORT).show();
                }
            });
            item.addView(btnRemove);

            mainScrollView.addView(item);
            labelJson.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnHome:
                Intent intent = new Intent(this, MainActivity.class);
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