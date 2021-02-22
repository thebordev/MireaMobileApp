package com.example.mireaapp.Frgaments.News;

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
import android.widget.Toast;

import com.example.mireaapp.Frgaments.News.adapter.NewsAdapter;
import com.example.mireaapp.Frgaments.News.model.Articles;
import com.example.mireaapp.Frgaments.News.model.Headlines;
import com.example.mireaapp.R;
import com.example.mireaapp.data.FakeDataSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NewsListFragment extends Fragment {

    RecyclerView recyclerView;
    NewsAdapter adapter;
    List<Articles> articles = new ArrayList<>();

    final String API_KEY = "your api key";

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
        recyclerView = view.findViewById(R.id.recyclerView_news);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        String country = getCountry();
        retrieveJson(country, API_KEY);


        // Inflate the layout for this fragment
        return view;
    }

    public void retrieveJson(String country, String apiKey) {
        Call<Headlines> call = ApiClient.getInstance().getApi().getHeadlines(country, apiKey);
        call.enqueue(new Callback<Headlines>() {
            @Override
            public void onResponse(Call<Headlines> call, Response<Headlines> response) {
                if (response.isSuccessful()) {
                    assert response.body() != null;
                    if (response.body().getArticles() != null) {
                        articles.clear();
                        articles = response.body().getArticles();
                        adapter = new NewsAdapter(getContext(), articles);
                        recyclerView.setAdapter(adapter);
                    }
                }
            }

            @Override
            public void onFailure(Call<Headlines> call, Throwable t) {
                Toast.makeText(getActivity(), t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    public String getCountry() {
        Locale locale = Locale.getDefault();
        String country = locale.getCountry();
        return country.toLowerCase();
    }


}