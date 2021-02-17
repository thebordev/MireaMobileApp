package com.example.mireaapp.Frgaments.News;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.data.NewsItems;
import com.example.mireaapp.databinding.ItemNewsImageBinding;
import com.example.mireaapp.databinding.ItemNewsTextBinding;


abstract class BaseViewHolder extends RecyclerView.ViewHolder {


    public BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bindData(NewsItems item);


    // we will need these methods for shared view animation purpose
    public abstract ItemNewsTextBinding getItemNewsTextBinding();
    public abstract ItemNewsImageBinding getItemNewsImageBinding();


}