package com.example.roomtest.Database;


import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

//这次存的是图片，而不是基本数据类型
@Entity
public class Article {
    //一会儿来试一下DataBinding能不能用- -
    @PrimaryKey(autoGenerate = true)
    private int Article_id;

    @ColumnInfo
    //不加@ColumnInfo 那使用数据库的各个列的名字是啥啊？
    //这一篇文章是否是正在创建当中，也就是是否是草稿阶段。你可以写多个草稿，而不必发布。最重要的是这个app创作的时候，应该是写一个字就保存一个字，确保无论因为任何原因中断都会有数据保存下来。提升用户的用户体验
    private boolean isInCreate;
    private String context;
    private String title;
//    private  保存图片咋整啊 - >  保存图片地址
    private String pic_address;

    public Article (boolean isInCreate, String title, String context, String pic_address) {
        this.isInCreate = isInCreate;
        this.context = context;
        this.title = title;
        this.pic_address = pic_address;
    }

    public int getArticle_id() {
        return Article_id;
    }

    public void setArticle_id(int article_id) {
        Article_id = article_id;
    }

    public boolean isInCreate() {
        return isInCreate;
    }

    public void setInCreate(boolean inCreate) {
        isInCreate = inCreate;
    }

    public String getContext() {
        return context;
    }

    public void setContext(String context) {
        this.context = context;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPic_address() {
        return pic_address;
    }

    public void setPic_address(String pic_address) {
        this.pic_address = pic_address;
    }
}
