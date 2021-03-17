package com.example.mireaapp.Frgaments.Explorer;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.browser.customtabs.CustomTabsIntent;
import androidx.core.app.ActivityOptionsCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mireaapp.Frgaments.Explorer.books.BookLibrary;
import com.example.mireaapp.Frgaments.Explorer.events.EventActivity;
import com.example.mireaapp.Frgaments.Explorer.news.News;
import com.example.mireaapp.Frgaments.Explorer.news.NewsActivity;
import com.example.mireaapp.Frgaments.Explorer.news.NewsAdapter;
import com.example.mireaapp.Frgaments.Explorer.news.NewsDetails;
import com.example.mireaapp.R;

import java.util.ArrayList;

public class ExplorerFragment extends Fragment {

    private LinearLayout bookItem, eventBtn, moreNewsBtn, forumBtn;

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
        View view = inflater.inflate(R.layout.fragment_explorer, container, false);

        bookItem = view.findViewById(R.id.bookItem);
        eventBtn = view.findViewById(R.id.event_btn);
        newsRecyclerView = view.findViewById(R.id.news_list);
        moreNewsBtn = view.findViewById(R.id.more_news_button);
        forumBtn = view.findViewById(R.id.forum_button);


        initNewsList();
        clckBook();
        clckEvent();
        clckForum();
        clckMoreNews();

        return view;
    }

    private void clckEvent() {
        eventBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), EventActivity.class);
                startActivity(intent);
            }
        });
    }
    private void clckForum() {
        forumBtn.setOnClickListener(new View.OnClickListener() {
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
    }
    private void clckBook() {
        bookItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), BookLibrary.class);
                startActivity(intent);
            }
        });
    }
    private void clckMoreNews() {
        moreNewsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), NewsActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initNewsList() {

        newsAdapter = new NewsAdapter(models, getContext());

        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        newsRecyclerView.hasFixedSize();
        newsRecyclerView.setAdapter(newsAdapter);

        models.add(new News("Открыт приём заявок на обучение по программам академического обмена в Национальном университете Ян Мин Цзяо Дун (Тайвань)", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_1, R.drawable.mosit));
        models.add(new News("Примите участие в масштабном опросе-исследовании РТУ МИРЭА «Я в теме!»", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_2, R.drawable.mosit));
        models.add(new News("Открыт приём научных докладов на V Международную научно-практическую конференцию «Радиоинфоком – 2021»\n", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_3, R.drawable.mosit));

        newsAdapter.notifyDataSetChanged();
    }

}