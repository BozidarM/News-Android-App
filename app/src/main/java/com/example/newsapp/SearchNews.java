package com.example.newsapp;

import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import static com.example.newsapp.showData.showSearchedItems;
import static com.example.newsapp.showData.showSearchedItemsSerbian;

public class SearchNews extends AppCompat implements View.OnClickListener {

    LinearLayout mainScrollView;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_news);
        setTitle(R.string.search);

        initComponents();
    }

    private void initComponents() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        HelpersFunctions hp = new HelpersFunctions(this);
        hp.bottomNavigationFunction(bottomNavigationView);

        MenuItem menuItem = bottomNavigationView.getMenu().getItem(3);
        menuItem.setChecked(true);
    }

    @SuppressLint("HandlerLeak")
    private void initNews(String searchValue) {

        LayoutInflater inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        LinearLayout mainScrollView = findViewById(R.id.mainScrollView);

        TextView labelJson = findViewById(R.id.labelJson);
        labelJson.setText(R.string.loading_news);

        LocaleHelper lh = new LocaleHelper(this);
        String lang = lh.getLang();

        if(lang.equals("sr")){
            showSearchedItemsSerbian(mainScrollView,labelJson, this, inflater, searchValue);
        }else{
            showSearchedItems(mainScrollView,labelJson, this, inflater, searchValue);
        }
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

                mainScrollView = findViewById(R.id.mainScrollView);
                int items_number = mainScrollView.getChildCount();
                if(items_number > 1){
                    mainScrollView.removeViews(1, items_number - 1);
                }

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

        HelpersFunctions hp = new HelpersFunctions(this);
        hp.clickOnOneItem(v);
    }
}