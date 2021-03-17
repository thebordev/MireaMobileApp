package com.example.mireaapp.Frgaments.Explorer.news;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.Frgaments.Explorer.ExplorerFragment;
import com.example.mireaapp.Frgaments.Explorer.events.SecondEventActivity;
import com.example.mireaapp.Frgaments.Notes.Note;
import com.example.mireaapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    
    ArrayList<News> models = new ArrayList<>();
    Context context;

    public NewsAdapter(ArrayList<News> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        holder.tvTitle.setText(models.get(position).getTitle());
        holder.tvDateTime.setText(models.get(position).getDateTime());
        holder.imageNews.setImageResource(models.get(position).getImageNews());
        holder.imageAuthor.setImageResource(models.get(position).getImageAuthor());
        //holder.tvDesc.setText(models.get(position).getDescription());
    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDateTime, tvDesc;
        ImageView imageNews, imageAuthor;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTitle = itemView.findViewById(R.id.titleNews);
            tvDateTime = itemView.findViewById(R.id.dateNews);
            imageNews = itemView.findViewById(R.id.iamgeNews);
            imageAuthor = itemView.findViewById(R.id.imageAuthorNews);

        }
    }
}
