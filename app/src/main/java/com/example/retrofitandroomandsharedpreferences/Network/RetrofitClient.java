package com.example.retrofitandroomandsharedpreferences.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClient {
    private RetrofitClient(){}
    private static Retrofit retrofit;
    public static NewsApi getApi() {
        if (retrofit == null) {
           // https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=9c00db9953cb4ba6b36eff4a15c43bc0
retrofit=new Retrofit.Builder().baseUrl("https://newsapi.org/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
return retrofit.create(NewsApi.class);
    }
}
