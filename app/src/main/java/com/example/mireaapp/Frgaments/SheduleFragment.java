package com.example.mireaapp.Frgaments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mireaapp.R;
import com.example.mireaapp.adapters.ScheduleItemsAdapter;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SheduleFragment extends Fragment {
    //Создаем список вьюх которые будут создаваться
    private RecyclerView allScheduleItems;
    private ScheduleItemsAdapter itemsAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View currentView = inflater.inflate(R.layout.fragment_shedule, container, false);
        allScheduleItems = (RecyclerView) currentView.findViewById(R.id.rv_all_schedul_items);

        itemsAdapter = new ScheduleItemsAdapter(100);
        allScheduleItems.setAdapter(itemsAdapter);

        return currentView;
    }
}