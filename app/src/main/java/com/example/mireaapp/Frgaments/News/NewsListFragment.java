package com.example.mireaapp.Frgaments.News;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mireaapp.Frgaments.News.books.Book;
import com.example.mireaapp.Frgaments.News.books.BookAdapter;
import com.example.mireaapp.Frgaments.News.books.BookLibrary;
import com.example.mireaapp.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class NewsListFragment extends Fragment {

    SliderView sliderView;
    LinearLayout bookItem;


    public NewsListFragment() {
        // Required empty public constructor

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_news_list, container, false);

        sliderView = view.findViewById(R.id.imageSlider_explorer);

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_1);
        images.add(R.drawable.banner_2);
        images.add(R.drawable.banner_3);
        images.add(R.drawable.error_image);
        NewsAdapter newsAdapter = new NewsAdapter(images);

        sliderView.setSliderAdapter(newsAdapter);
        sliderView.setAutoCycle(true);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);

        bookItem = view.findViewById(R.id.bookItem);
        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookLibrary.class);
                startActivity(intent);
            }
        });


        // Inflate the layout for this fragment
        return view;
    }

}