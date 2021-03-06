package com.example.mireaapp.Frgaments.Explorer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mireaapp.ForumActivity;
import com.example.mireaapp.Frgaments.Explorer.books.BookLibrary;
import com.example.mireaapp.Frgaments.Explorer.news.News;
import com.example.mireaapp.Frgaments.Explorer.news.NewsAdapter;
import com.example.mireaapp.R;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;

public class ExplorerFragment extends Fragment {

    private SliderView sliderView;
    private LinearLayout bookItem;

    private RecyclerView newsRecyclerView;
    private NewsAdapter newsAdapter;
    ArrayList<News> models = new ArrayList<>();

    public ExplorerFragment() {
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
        bookItem = view.findViewById(R.id.bookItem);
        newsRecyclerView = view.findViewById(R.id.news_list);
        LinearLayout forumButton = view.findViewById(R.id.forum_button);

        forumButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Uri uri = Uri.parse("https://mirea.ninja");

                CustomTabsIntent.Builder intentBuilder = new CustomTabsIntent.Builder();

                intentBuilder.setToolbarColor(ContextCompat.getColor(getContext(), R.color.bg_table_shedule_dark));
                intentBuilder.setSecondaryToolbarColor(ContextCompat.getColor(getContext(), R.color.dark_bg_nav_bottom));

                CustomTabsIntent customTabsIntent = intentBuilder.build();

                customTabsIntent.launchUrl(getContext(), uri);
            }
        });


        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookLibrary.class);
                startActivity(intent);
            }
        });

        initBanners();
        initNewsList();

        // Inflate the layout for this fragment
        return view;
    }

    private void initBanners() {

        List<Integer> images = new ArrayList<>();
        images.add(R.drawable.banner_1);
        images.add(R.drawable.banner_2);
        images.add(R.drawable.banner_3);
        images.add(R.drawable.error_image);
        NewsBannerAdapter newsAdapter = new NewsBannerAdapter(images);

        sliderView.setSliderAdapter(newsAdapter);
        sliderView.setAutoCycle(true);
        sliderView.setSliderTransformAnimation(SliderAnimations.ZOOMOUTTRANSFORMATION);

    }

    private void initNewsList() {

        newsAdapter = new NewsAdapter(models, getContext());

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        newsRecyclerView.hasFixedSize();
        newsRecyclerView.setAdapter(newsAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", "", ""));

                newsAdapter.notifyDataSetChanged();
            }
        }, 10000);




    }

}