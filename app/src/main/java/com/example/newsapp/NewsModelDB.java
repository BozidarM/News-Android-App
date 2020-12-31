package com.example.newsapp;

public class NewsModelDB {


    public static final String TABLE_NAME = "post";

    public static final String FIELD_POST_ID = "post_id";
    public static final String FIELD_TITLE = "title";
    public static final String FIELD_AUTHOR = "author";
    public static final String FIELD_PUBLISHED_AT = "published_at";
    public static final String FIELD_IMAGE = "image";
    public static final String FIELD_LINK = "link";

    private int postId;
    private String title, author, published_at, image, link;

    public NewsModelDB(int postId, String title, String author, String published_at, String image, String link) {
        this.postId = postId;
        this.title = title;
        this.author = author;
        this.published_at = published_at;
        this.image = image;
        this.link = link;
    }

    public int getPostId() {
        return postId;
    }

    public void setPostId(int postId) {
        this.postId = postId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPublished_at() {
        return published_at;
    }

    public void setPublished_at(String published_at) {
        this.published_at = published_at;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @Override
    public String toString() {
        return "NewsModelDB{" +
                "postId=" + postId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", published_at='" + published_at + '\'' +
                ", image='" + image + '\'' +
                ", link='" + link + '\'' +
                '}';
    }
}
