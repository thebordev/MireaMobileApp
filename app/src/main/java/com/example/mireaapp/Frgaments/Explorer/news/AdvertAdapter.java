package com.example.mireaapp.Frgaments.Explorer.news;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

import java.util.ArrayList;

public class AdvertAdapter extends RecyclerView.Adapter<AdvertAdapter.ViewHolder> {

    ArrayList<Advert> models = new ArrayList<>();
    Context context;

    public AdvertAdapter(ArrayList<Advert> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news_page, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.tvTittle.setText(models.get(position).getTitle());
        holder.tvDescription.setText(models.get(position).getDescription());
        holder.tvDateTime.setText(models.get(position).getDateTime());
        holder.imageView.setImageResource(models.get(position).getImageUrl());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTittle, tvDateTime, tvDescription;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTittle = itemView.findViewById(R.id.textTitle_newsPage);
            tvDateTime = itemView.findViewById(R.id.textDateTime_newsPage);
            tvDescription = itemView.findViewById(R.id.textDescription_newsPage);
            imageView = itemView.findViewById(R.id.imageNote_newsPage);
        }
    }
}
