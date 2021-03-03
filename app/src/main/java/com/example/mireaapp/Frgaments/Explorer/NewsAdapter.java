package com.example.mireaapp.Frgaments.Explorer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.mireaapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

import java.util.List;

public class NewsAdapter extends SliderViewAdapter<NewsAdapter.MyViewHolder> {

    List<Integer> imageList;

    NewsAdapter(List<Integer> list) {
        this.imageList = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_banner_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHolder viewHolder, int position) {
        viewHolder.imageView.setImageResource(imageList.get(position));

    }

    @Override
    public int getCount() {
        return imageList.size();
    }

    public class MyViewHolder extends SliderViewAdapter.ViewHolder
    {

        ImageView imageView;

        public MyViewHolder(View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageViewBanner_item);
        }
    }
}
