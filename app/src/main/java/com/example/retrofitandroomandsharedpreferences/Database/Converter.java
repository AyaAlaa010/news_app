package com.example.retrofitandroomandsharedpreferences.Database;

import androidx.room.TypeConverter;

import com.example.retrofitandroomandsharedpreferences.Models.Source;
import com.google.gson.Gson;

public class Converter {
    @TypeConverter
    public  String fromSource(Source source){
        return new Gson().toJson(source);
    }
    @TypeConverter
    public  Source toSource(String json ){
        //Source source = new Gson().fromJson(json, Source.class);
        return new Gson().fromJson(json, Source.class);

    }}
