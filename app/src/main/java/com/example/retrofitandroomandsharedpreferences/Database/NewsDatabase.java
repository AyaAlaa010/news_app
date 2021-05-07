package com.example.retrofitandroomandsharedpreferences.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

import com.example.retrofitandroomandsharedpreferences.Models.Article;
@TypeConverters(Converter.class)
@Database(entities= Article.class,exportSchema = false,version = 1)
public  abstract class NewsDatabase  extends RoomDatabase {
 //   private NewsDatabase(){}// for prevent any one for taking object

    public abstract InterfaceNewsDao newsDao();
    private static NewsDatabase instance;

    public static NewsDatabase getInstance(Context context) {
        if(instance==null){
            instance = Room.databaseBuilder(context, NewsDatabase.class, "News DB").allowMainThreadQueries().build();}
        return instance;}






}
