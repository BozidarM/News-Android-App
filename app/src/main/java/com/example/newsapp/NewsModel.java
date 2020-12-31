package com.example.newsapp;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.LinkedList;

public class NewsModel {

    private String author, title, desc, sourceLink, image, publishedAt;

    public NewsModel(){

    }

    public NewsModel(String author, String title, String desc, String sourceLink, String image, String publishedAt) {
        this.author = author;
        this.title = title;
        this.desc = desc;
        this.sourceLink = sourceLink;
        this.image = image;
        this.publishedAt = publishedAt;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public static NewsModel parseJSONObject (JSONObject object){
        NewsModel post = new NewsModel();

        try{
            if (object.has("author")) {
                post.setAuthor(object.getString("author"));
            }
            if (object.has("title")) {
                post.setTitle(object.getString("title"));
            }
            if (object.has("description")) {
                post.setDesc(object.getString("description"));
            }
            if (object.has("url")) {
                post.setSourceLink(object.getString("url"));
            }
            if (object.has("image")) {
                post.setImage(object.getString("image"));
            }
            if (object.has("published")) {
                post.setPublishedAt(object.getString("published"));
            }


        } catch (Exception e){}

        return post;
    }

    public static LinkedList<NewsModel> parseJSONArray (JSONArray array){
        LinkedList<NewsModel> list = new LinkedList<>();

        try{
            for(int i = 0; i < array.length(); i++){
                NewsModel post = parseJSONObject(array.getJSONObject(i));
                list.add(post);
            }
        } catch (Exception e){}

        return list;

    }
}