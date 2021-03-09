package com.example.mireaapp.Frgaments.Explorer.news;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mireaapp.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AnnouncementsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AnnouncementsFragment extends Fragment {
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_announcements, container, false);
    }
}