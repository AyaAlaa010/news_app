package com.example.retrofitandroomandsharedpreferences.Network;

import com.example.retrofitandroomandsharedpreferences.Models.NewsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NewsApi {
    // https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=9c00db9953cb4ba6b36eff4a15c43bc0
    @GET("v2/top-headlines?apiKey=9c00db9953cb4ba6b36eff4a15c43bc0")
    Call<NewsResponse> getNews(@Query ("country") String country
    ,@Query("category") String category);




}
