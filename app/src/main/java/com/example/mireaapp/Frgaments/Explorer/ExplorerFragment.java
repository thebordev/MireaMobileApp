package com.example.mireaapp.Frgaments.Explorer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.example.mireaapp.Frgaments.Explorer.books.BookLibrary;
import com.example.mireaapp.Frgaments.Explorer.events.EventActivity;
import com.example.mireaapp.Frgaments.Explorer.news.News;
import com.example.mireaapp.Frgaments.Explorer.news.NewsActivity;
import com.example.mireaapp.Frgaments.Explorer.news.NewsAdapter;
import com.example.mireaapp.Frgaments.Explorer.stories.StoriesAdapter;
import com.example.mireaapp.Frgaments.Explorer.stories.Story;
import com.example.mireaapp.R;

import java.util.ArrayList;

public class ExplorerFragment extends Fragment {

    private LinearLayout bookItem, eventBtn, moreNewsBtn;

    private RecyclerView newsRecyclerView, storiesBar;
    private NewsAdapter newsAdapter;
    private StoriesAdapter storiesAdapter;

    ArrayList<News> models = new ArrayList<>();
    ArrayList<Story> stories = new ArrayList<>();

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

        bookItem = view.findViewById(R.id.bookItem);
        eventBtn = view.findViewById(R.id.event_btn);
        newsRecyclerView = view.findViewById(R.id.news_list);
        storiesBar = view.findViewById(R.id.storiesBar_explorer);
        moreNewsBtn = view.findViewById(R.id.more_news_button);

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

        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });

        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookLibrary.class);
                startActivity(intent);
            }
        });

        moreNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });

        initStories();
        initNewsList();

        // Inflate the layout for this fragment
        return view;
    }

    private void initStories() {

        storiesBar.setVisibility(View.VISIBLE);

        storiesAdapter = new StoriesAdapter(getContext(), stories);

        stories.add(new Story("mirea"));

        storiesBar.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        storiesBar.hasFixedSize();
        storiesBar.setAdapter(storiesAdapter);


    }

    private void initNewsList() {

        newsAdapter = new NewsAdapter(models, getContext());

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        newsRecyclerView.hasFixedSize();
        newsRecyclerView.setAdapter(newsAdapter);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_1, ""));
                models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_2, ""));
                models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_3, ""));

                newsAdapter.notifyDataSetChanged();
            }
        }, 10000);




    }

}