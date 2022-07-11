package com.example.newsapp;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.kwabenaberko.newsapilib.NewsApiClient;
import com.kwabenaberko.newsapilib.models.Article;
import com.kwabenaberko.newsapilib.models.request.EverythingRequest;
import com.kwabenaberko.newsapilib.models.request.TopHeadlinesRequest;
import com.kwabenaberko.newsapilib.models.response.ArticleResponse;
import com.squareup.picasso.Picasso;

public class showData{

    @SuppressLint("ResourceAsColor")
    public static void showDataFunction(LinearLayout mainScrollView, View.OnClickListener fragment, LayoutInflater inflater, ArticleResponse response, TextView labelJson) {

        if(response.getTotalResults() > 0) {

            for (Article oneItem : response.getArticles()) {

                CardView item = (CardView) inflater.inflate(R.layout.one_item, null);
                item.setOnClickListener(fragment);

                TextView twTitle = item.findViewById(R.id.twTitle);
                twTitle.setText(oneItem.getTitle());
                TextView twAuthor = item.findViewById(R.id.twAuthor);
                twAuthor.setText(oneItem.getSource().getName());
                TextView twPublished = item.findViewById(R.id.twPublished);
                String date = oneItem.getPublishedAt();
                String ymd = date.substring(0, 16);
                String ymdt = ymd.replace("T", " ");
                twPublished.setText(ymdt);
                TextView twLink = item.findViewById(R.id.twLink);
                twLink.setText(oneItem.getUrl());
                TextView twImage = item.findViewById(R.id.twImage);
                twImage.setText(oneItem.getUrlToImage());

                ImageView image = item.findViewById(R.id.imageThumb);
                if (oneItem.getUrlToImage() == null) {
                    Picasso.get().load("https://kwx2f3rgme1bvul52zzobow3-wpengine.netdna-ssl.com/wp-content/themes/realestate-7/images/no-image.png").resize(150, 136).into(image);
                } else {
                    String img_url = oneItem.getUrlToImage();
                    if (!(img_url.contains("https") || img_url.contains("http"))) {
                        img_url = "https:" + img_url;
                    }
                    Picasso.get().load(img_url).resize(150, 136).into(image);
                }
                mainScrollView.addView(item);
                labelJson.setVisibility(View.GONE);
            }

        }else{
            labelJson.setText(R.string.nothing_found);
        }
    }

    public static void showTopHeadlines(LinearLayout mainScrollView, TextView labelJson, View.OnClickListener fragment, LayoutInflater inflater, String category){
        NewsApiClient newsApiClient = new NewsApiClient("2d3c937cf74f4fc1aa2b739d20540512");

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .category(category)
                        .pageSize(30)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        showDataFunction(mainScrollView, fragment, inflater, response, labelJson);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    public static void showTopHeadlinesSerbian(LinearLayout mainScrollView, TextView labelJson, View.OnClickListener fragment, LayoutInflater inflater, String category){
        NewsApiClient newsApiClient = new NewsApiClient("2d3c937cf74f4fc1aa2b739d20540512");

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country("rs")
                        .category(category)
                        .pageSize(30)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        showDataFunction(mainScrollView, fragment, inflater, response, labelJson);
                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    public static void showSearchedItems(LinearLayout mainScrollView, TextView labelJson, View.OnClickListener fragment, LayoutInflater inflater, String searchVal){
        NewsApiClient newsApiClient = new NewsApiClient("2d3c937cf74f4fc1aa2b739d20540512");

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .language("en")
                        .q(searchVal)
                        .pageSize(30)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        showDataFunction(mainScrollView, fragment, inflater, response, labelJson);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    public static void showSearchedItemsSerbian(LinearLayout mainScrollView, TextView labelJson, View.OnClickListener fragment, LayoutInflater inflater, String searchVal){
        NewsApiClient newsApiClient = new NewsApiClient("2d3c937cf74f4fc1aa2b739d20540512");

        newsApiClient.getTopHeadlines(
                new TopHeadlinesRequest.Builder()
                        .country("rs")
                        .q(searchVal)
                        .pageSize(30)
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        showDataFunction(mainScrollView, fragment, inflater, response, labelJson);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }

    public static void showOlderNews(LinearLayout mainScrollView, TextView labelJson, View.OnClickListener fragment, LayoutInflater inflater, String date, String keyword){
        NewsApiClient newsApiClient = new NewsApiClient("2d3c937cf74f4fc1aa2b739d20540512");

        newsApiClient.getEverything(
                new EverythingRequest.Builder()
                        .q(keyword)
                        .from(date)
                        .to(date)
                        .language("en")
                        .build(),
                new NewsApiClient.ArticlesResponseCallback() {
                    @Override
                    public void onSuccess(ArticleResponse response) {

                        showDataFunction(mainScrollView, fragment, inflater, response, labelJson);

                    }

                    @Override
                    public void onFailure(Throwable throwable) {
                        System.out.println(throwable.getMessage());
                    }
                }
        );
    }
}