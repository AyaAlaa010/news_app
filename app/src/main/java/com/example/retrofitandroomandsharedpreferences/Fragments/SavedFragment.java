package com.example.retrofitandroomandsharedpreferences.Fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.retrofitandroomandsharedpreferences.Adapter.NewsAdapter;
import com.example.retrofitandroomandsharedpreferences.Database.NewsDatabase;
import com.example.retrofitandroomandsharedpreferences.Models.Article;
import com.example.retrofitandroomandsharedpreferences.R;

import java.util.List;

import es.dmoral.toasty.Toasty;


public class SavedFragment extends Fragment {
RecyclerView recyclerView;
NewsAdapter myadapter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_saved, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView=view.findViewById(R.id.savedrecycler);
            getSavedNews();
    }
    private void getSavedNews(){
        List<Article> articles= NewsDatabase.getInstance(requireContext()).newsDao().getArticles();
        myadapter=new NewsAdapter(articles,newsI);
        recyclerView.setAdapter(myadapter);
        if(articles.isEmpty()){
            Toasty.info(requireContext(),"no saved articles",Toasty.LENGTH_LONG).show();
        }
    }

    NewsAdapter.NewsI newsI=new NewsAdapter.NewsI() {
        @Override
        public void onActionClick(Article article) {
new AlertDialog.Builder(requireContext()).setMessage("are you sure to delete this article")
        .setNegativeButton("cancle",null)
        .setPositiveButton("delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                NewsDatabase.getInstance(requireContext()).newsDao().deleteArticle(article);
                Toasty.info(requireContext(),"Deleted",Toasty.LENGTH_LONG).show();
                getSavedNews();
            }
        }).show();
        }
    };
}