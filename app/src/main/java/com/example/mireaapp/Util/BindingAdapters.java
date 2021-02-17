package com.example.mireaapp.Util;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

import com.bumptech.glide.Glide;
import com.example.mireaapp.R;

public class BindingAdapters {

    @BindingAdapter("glide_url")
    public static void loadImage(ImageView imageView, String url) {

        Glide.with(imageView.getContext()).load(url).into(imageView);
    }

    @BindingAdapter("glide_circulac")
    public static void loadUserImg(ImageView view, String url) {
        Glide.with(view.getContext()).load(url).circleCrop().into(view);
    }

    @BindingAdapter("set_background")
    public static void setBackground(ImageView view,String color) {

        switch (color) {
            case "RED" : view.setBackgroundColor(view.getContext().getResources().getColor(R.color.red));
                break;
            case "BLACK" : view.setBackgroundColor(view.getContext().getResources().getColor(R.color.black));
                break;
            case "YELLOW" : view.setBackgroundColor(view.getContext().getResources().getColor(R.color.yellow));
                break;
            case "BLUE" : view.setBackgroundColor(view.getContext().getResources().getColor(R.color.blue));
                break;
            case "PURPLE" : view.setBackgroundColor(view.getContext().getResources().getColor(R.color.purple));
                break;
            default: view.setBackgroundColor(view.getContext().getResources().getColor(R.color.red));
        }

    }

}
