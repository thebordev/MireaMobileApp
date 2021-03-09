package com.example.mireaapp.Frgaments.Explorer.news;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mireaapp.R;
import com.example.mireaapp.Util.CalendarManager;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity {

    ViewPager2 newsList;
    FragmentStateAdapter pageAdapter;

    LinearLayout buttonNews, buttonAnnounce;
    TextView buttonNewsText, buttonAnnounceText;

    ArrayList<News> models = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        newsList = findViewById(R.id.news_list_news);

        pageAdapter = new NewsPageAdapter(this);
        newsList.setAdapter(pageAdapter);

        buttonNews = findViewById(R.id.button_news);
        buttonAnnounce = findViewById(R.id.button_announce);
        buttonNewsText = findViewById(R.id.button_news_text);
        buttonAnnounceText = findViewById(R.id.button_announce_text);

        buttonNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsList.setCurrentItem(0);
            }
        });

        buttonAnnounce.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newsList.setCurrentItem(1);
            }
        });

        newsList.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                switch (position){
                    case 0:
                        // active:
                        buttonNews.setBackgroundResource(R.drawable.bg_dialog);
                        buttonNewsText.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.ligh_medium_gray));

                        // inactive:
                        buttonAnnounceText.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.dark_gray_darker));
                        buttonAnnounce.setBackgroundResource(0);
                        break;
                    case 1:
                        // active:
                        buttonAnnounce.setBackgroundResource(R.drawable.bg_dialog);
                        buttonAnnounceText.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.ligh_medium_gray));

                        // inactive:
                        buttonNews.setBackgroundResource(0);
                        buttonNewsText.setTextColor(ContextCompat.getColor(getBaseContext(), R.color.dark_gray_darker));
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
    }
}