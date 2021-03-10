package com.example.mireaapp.Frgaments.Explorer.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mireaapp.R;

import java.util.ArrayList;


public class NewsFragment extends Fragment {
    private RecyclerView newsRecyclerView;
    ArrayList<News> models = new ArrayList<>();
    private NewsAdapter newsAdapter;

    public static NewsFragment newInstance() {
        NewsFragment fragment = new NewsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_news, container, false);
        newsRecyclerView = view.findViewById(R.id.news_list);

        initNews();

        return view;
    }

    private void initNews() {
        newsAdapter = new NewsAdapter(models, getContext());
        newsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
        newsRecyclerView.hasFixedSize();
        newsRecyclerView.setAdapter(newsAdapter);


        models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_1, ""));
        models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_2, ""));
        models.add(new News("test", "Fasgnenjfk", "Май 13, 2020", R.drawable.banner_3, ""));

        newsAdapter.notifyDataSetChanged();
    }

}