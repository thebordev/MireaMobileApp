package com.example.mireaapp.Frgaments.Explorer.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.example.mireaapp.R;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ViewPager2 newsList;
    NewsPageAdapter newsPageAdapter;

    ArrayList<News> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsList = findViewById(R.id.news_list_news);

    }

    private void initNews() {
        newsPageAdapter = new NewsPageAdapter(models, this);




    }
}