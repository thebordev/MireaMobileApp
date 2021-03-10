package com.example.mireaapp.Frgaments.Explorer.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mireaapp.R;

import java.util.ArrayList;


public class AnnouncementsFragment extends Fragment {

    private RecyclerView announceRecyclerView;
    ArrayList<Advert> models = new ArrayList<>();
    private AdvertAdapter announceAdapter;

    public static AnnouncementsFragment newInstance() {
        AnnouncementsFragment fragment = new AnnouncementsFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_announcements, container, false);
        announceRecyclerView = view.findViewById(R.id.announce_list);

        initAnnounce();

        return view;
    }

    private void initAnnounce() {

        announceAdapter = new AdvertAdapter(models, getContext());
        announceRecyclerView.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false));
        announceRecyclerView.hasFixedSize();
        announceRecyclerView.setAdapter(announceAdapter);

        models.add(new Advert("test", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_1));
        models.add(new Advert("test", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_2));
        models.add(new Advert("test", "Fasgnenjfk", "Май 13, 2020", "", R.drawable.banner_3));

        announceAdapter.notifyDataSetChanged();

    }
}