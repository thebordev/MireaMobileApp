package com.example.mireaapp.Frgaments.Explorer.events;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityOptionsCompat;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.mireaapp.R;

import java.util.ArrayList;
import java.util.List;

public class EventActivity extends AppCompatActivity {

    private ViewPager2 locationsViewPager;
    private List<TravelLocation> travelLocations = new ArrayList<>();
    private TravelLocationAdapter travelLocationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);

        locationsViewPager = findViewById(R.id.locationsViewPager);

        initEvents();

    }

    private void initEvents() {
        TravelLocation travelLocationEiffelTower = new TravelLocation();
        travelLocationEiffelTower.imageUrl = "https://img.rl0.ru/afisha/e1425x801p0x0f1280x730q65i/s2.afisha.ru/mediastorage/3d/1e/456ecc28cec5483399456e171e3d.jpg";
        travelLocationEiffelTower.title = "«Мумий Тролль»";
        travelLocationEiffelTower.location = "Москва, ш. Энтузиастов, 5";
        travelLocationEiffelTower.starRating = 4.8f;
        travelLocations.add(travelLocationEiffelTower);

        TravelLocation travelLocationEiffelMahal = new TravelLocation();
        travelLocationEiffelMahal.imageUrl = "https://img08.rl0.ru/afisha/e1425x801p230x129f2586x1478q65i/s1.afisha.ru/mediastorage/7a/a8/c1e82c447e7542099daf134da87a.jpg";
        travelLocationEiffelMahal.title = "The Hatters";
        travelLocationEiffelMahal.location = "Москва, Ленинградский просп., 80, корп. 17";
        travelLocationEiffelMahal.starRating = 4.5f;
        travelLocations.add(travelLocationEiffelMahal);

        locationsViewPager.setAdapter(new TravelLocationAdapter(travelLocations, this));

        locationsViewPager.setClipToPadding(false);
        locationsViewPager.setClipChildren(false);
        locationsViewPager.setOffscreenPageLimit(3);
        locationsViewPager.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER);

        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1 - Math.abs(position);
                page.setScaleY(0.95f + r * 0.05f);
            }
        });

        locationsViewPager.setPageTransformer(compositePageTransformer);
    }

}