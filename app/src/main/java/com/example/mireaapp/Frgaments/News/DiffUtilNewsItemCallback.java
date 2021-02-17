package com.example.mireaapp.Frgaments.News;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;

import com.example.mireaapp.data.NewsItems;

public class DiffUtilNewsItemCallback extends DiffUtil.ItemCallback<NewsItems> {
    @Override
    public boolean areItemsTheSame(@NonNull NewsItems oldItem, @NonNull NewsItems newItem) {
        return oldItem.getId() == newItem.getId();
    }

    @Override
    public boolean areContentsTheSame(@NonNull NewsItems oldItem, @NonNull NewsItems newItem) {
        return oldItem.getId() == newItem.getId();
    }
}