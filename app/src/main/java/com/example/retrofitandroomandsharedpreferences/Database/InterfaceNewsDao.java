package com.example.retrofitandroomandsharedpreferences.Database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.retrofitandroomandsharedpreferences.Models.Article;

import java.util.List;

@Dao
public interface InterfaceNewsDao {
  @Query("SELECT * FROM articles")
    List<Article> getArticles();
  @Delete
     void deleteArticle(Article article);

  @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertArticle(Article article);

}
