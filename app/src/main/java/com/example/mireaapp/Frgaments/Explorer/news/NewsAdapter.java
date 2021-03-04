package com.example.mireaapp.Frgaments.Explorer.news;

import android.content.Context;
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
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.Frgaments.Explorer.ExplorerFragment;
import com.example.mireaapp.Frgaments.Notes.Note;
import com.example.mireaapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    
    ArrayList<News> models = new ArrayList<>();
    Context context;
    
    boolean isShimmer = true;
    int shimmerNumber = 5;

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


    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        
        if (isShimmer) {
            holder.shimmerFrameLayout.startShimmer();
        } else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);
            
            holder.tvTitle.setBackground(null);
            holder.tvTitle.setText(models.get(position).getTitle());
            
            holder.tvDateTime.setBackground(null);
            holder.tvDateTime.setText(models.get(position).getDateTime());
            
            holder.imageView.setImageDrawable(context.getDrawable(R.drawable.banner_1));
        }

    }

    @Override
    public int getItemCount() {
        return isShimmer?shimmerNumber:models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ShimmerFrameLayout shimmerFrameLayout;
        TextView tvTitle, tvDateTime;
        ImageView imageView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            shimmerFrameLayout = itemView.findViewById(R.id.shimmerNews);
            tvTitle = itemView.findViewById(R.id.textTitle_news);
            tvDateTime = itemView.findViewById(R.id.textDateTime_news);
            imageView = itemView.findViewById(R.id.imageNote_news);

        }
    }
}
