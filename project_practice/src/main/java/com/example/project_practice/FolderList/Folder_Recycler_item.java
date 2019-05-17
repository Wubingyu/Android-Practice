package com.example.project_practice.FolderList;

public class Folder_Recycler_item {
    int img_id;
    String title;
    int[] Article_ID;

    public Folder_Recycler_item(int img_id, String title, int[] article_ID) {
        this.img_id = img_id;
        this.title = title;
        Article_ID = article_ID;
    }

    public int getImg_id() {
        return img_id;
    }

    public void setImg_id(int img_id) {
        this.img_id = img_id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int[] getArticle_ID() {
        return Article_ID;
    }

    public void setArticle_ID(int[] article_ID) {
        Article_ID = article_ID;
    }
}
