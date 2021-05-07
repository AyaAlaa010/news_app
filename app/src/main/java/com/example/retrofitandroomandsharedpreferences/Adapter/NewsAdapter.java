package com.example.retrofitandroomandsharedpreferences.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.retrofitandroomandsharedpreferences.Models.Article;
import com.example.retrofitandroomandsharedpreferences.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsHolder> {
   private final List<Article> articles;
private  final NewsI newsI;
    public NewsAdapter(List<Article> articles,NewsI newsI) {
        this.articles = articles;
        this.newsI=newsI;
    }

    @NonNull
    @Override
    public NewsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.items_layout,parent,false);
        NewsHolder newsHolder=new NewsHolder(view);
        return newsHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull NewsHolder holder, int position) {
Article article=articles.get(position);
        holder.tvNews.setText(article.getTitle());
        Picasso.get().load(article.getUrlToImage()).placeholder(R.mipmap.ic_launcher_round).into((holder.imgNews));
        holder.imgAction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsI.onActionClick(article);
            }
        });


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public interface NewsI {
        public void onActionClick(Article article);
    }
    class NewsHolder extends RecyclerView.ViewHolder{
ImageView imgAction,imgNews;
TextView tvNews;

        public NewsHolder(@NonNull View itemView) {
            super(itemView);

            imgAction=(ImageView) itemView.findViewById(R.id.imge_icon);
            imgNews=(ImageView) itemView.findViewById(R.id.img_item);
            tvNews=itemView.findViewById(R.id.tv_item_news);


        }
    }



}
