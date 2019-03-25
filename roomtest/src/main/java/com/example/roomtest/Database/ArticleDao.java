package com.example.roomtest.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;


@Dao
public interface ArticleDao {
    //D:\Android-project\~github-project\DiaryList\roomtest\src\main\java\com\example\roomtest\Database\ArticleDao.java:15:
    // 错误: There is a problem with the query: [SQLITE_ERROR] SQL error or missing database
    // (no such table: article)
    @Query("SELECT * FROM article")
    List<Article> getAllarticle();

    @Query("SELECT * FROM article WHERE Article_id LIKE :search_id")
    Article getArticleById(int search_id);

    //这个查询是这样写的吗？
    @Query("SELECT * FROM article WHERE Title like ('%' + :title_part + '%') ")
    List<Article> searchArticleByTitle(String title_part);

    @Insert()
    void insertAll(Article... articles);

    //错误: com.example.roomtest.Database.ArticleDao is part of com.example.roomtest.Database.AppDatabase
    // but this entity is not in the database.
    // Maybe you forgot to add com.example.roomtest.Database.Article to the entities section of the @Database? void delete(Article article);
    @Delete
    void delete(Article article);

    @Update
    void update(Article article);


}
