package com.example.mireaapp.Frgaments.News;

import androidx.annotation.NonNull;

import com.example.mireaapp.data.NewsItems;
import com.example.mireaapp.databinding.ItemNewsImageBinding;
import com.example.mireaapp.databinding.ItemNewsTextBinding;


public class ImageViewHolder extends BaseViewHolder {

    ItemNewsImageBinding itemNewsImageBinding;

    public ImageViewHolder(@NonNull ItemNewsImageBinding itemNewsImageBinding) {
        super(itemNewsImageBinding.getRoot());
        this.itemNewsImageBinding = itemNewsImageBinding;
    }

    @Override
    public void bindData(NewsItems item) {

        itemNewsImageBinding.setNewsItemImage(item);

    }

    @Override
    public ItemNewsTextBinding getItemNewsTextBinding() {
        return null;
    }

    @Override
    public ItemNewsImageBinding getItemNewsImageBinding() {
        return null;
    }
}