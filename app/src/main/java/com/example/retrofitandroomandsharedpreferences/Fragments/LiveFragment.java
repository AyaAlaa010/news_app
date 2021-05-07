package com.example.retrofitandroomandsharedpreferences.Fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ProgressBar;

import com.example.retrofitandroomandsharedpreferences.Adapter.NewsAdapter;
import com.example.retrofitandroomandsharedpreferences.Database.NewsDatabase;
import com.example.retrofitandroomandsharedpreferences.Models.Article;
import com.example.retrofitandroomandsharedpreferences.Models.NewsResponse;
import com.example.retrofitandroomandsharedpreferences.Network.RetrofitClient;
import com.example.retrofitandroomandsharedpreferences.R;

import es.dmoral.toasty.Toasty;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LiveFragment extends Fragment {
    String []categories={"sports","technology"};
    AutoCompleteTextView autoCompleteTextView;
    ProgressBar progressBar;
NewsAdapter adapter;
RecyclerView recyclerView;
int lastPosition;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_live, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.myrecycler);
        autoCompleteTextView=view.findViewById(R.id.myautocomplete);
        progressBar=view.findViewById(R.id.progress);
        lastPosition=getActivity().getSharedPreferences("news",Context.MODE_PRIVATE).getInt("lastposition",0);
        initCategories();
        getLiveNews(categories[lastPosition]);
    }
    @SuppressLint("NewApi")
    public void initCategories(){
    autoCompleteTextView.setText(categories[lastPosition],false);

    ArrayAdapter<String>arrayAdapter=new ArrayAdapter<>(requireContext(), android.R.layout.simple_list_item_1,categories);

       autoCompleteTextView.setAdapter(arrayAdapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                getLiveNews(categories[position]);
                savedLastPosition(position);
            }
        });

    }
    public  void savedLastPosition(int position){
SharedPreferences preferences=getActivity().getSharedPreferences("news", Context.MODE_PRIVATE);
                 preferences.edit().putInt("lastposition",position).apply();

    }

    private void getLiveNews(String category) {
        RetrofitClient.getApi().getNews("eg",category).enqueue(new Callback<NewsResponse>() {
            @Override
            public void onResponse(Call<NewsResponse> call, Response<NewsResponse> response) {
if(response.isSuccessful()&&response.body()!=null){
    adapter=new NewsAdapter(response.body().getArticles(),newsI);
    recyclerView.setAdapter(adapter);
    progressBar.setVisibility(View.GONE);
}
            }

            @Override
            public void onFailure(Call<NewsResponse> call, Throwable t) {
                String errorMessage = t.getLocalizedMessage();
                Toasty.error(requireContext(),errorMessage,Toasty.LENGTH_LONG).show();

            }
        });
    }
NewsAdapter.NewsI newsI=new NewsAdapter.NewsI() {
    @Override
    public void onActionClick(Article article) {
        NewsDatabase.getInstance(requireContext()).newsDao().insertArticle(article);
        Toasty.success(requireContext(),"Article saved",Toasty.LENGTH_LONG).show();
    }
};
}