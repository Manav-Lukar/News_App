package com.example.news_app.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.news_app.R;
import com.example.news_app.databinding.ItemNewsBinding;
import com.example.news_app.model.NewsData;
import com.squareup.picasso.Picasso;

import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    List<NewsData> newsDataList;

    public NewsAdapter(List<NewsData> newsDataList) {
        this.newsDataList = newsDataList;
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_news, parent, false);
        return new NewsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        NewsData nd = newsDataList.get(position);
        holder.binding.txtTitle.setText(nd.getTitle());
        holder.binding.txtContent.setText(nd.getContent());
        Picasso.get().load(nd.getImgUrl()).into(holder.binding.imgNews);
    }

    @Override
    public int getItemCount() {
        return newsDataList.size();
    }

    class NewsViewHolder extends RecyclerView.ViewHolder {

        ItemNewsBinding binding;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            binding = ItemNewsBinding.bind(itemView);
        }
    }

}
