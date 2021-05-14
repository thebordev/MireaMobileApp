package com.example.mireaapp.Frgaments;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.example.mireaapp.R;
import com.github.chrisbanes.photoview.PhotoView;

public class MapFragment extends Fragment {

    private LinearLayout btn0, btn1, btn2, btn3, btn4;
    private SubsamplingScaleImageView floor_iamge;
    private TextView txt0, txt1, txt2, txt3, txt4;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_map, container, false);

        //image map
        floor_iamge = view.findViewById(R.id.map_image);

        //btn floor
        btn0 = view.findViewById(R.id.btn_fl0);
        btn1 = view.findViewById(R.id.btn_fl1);
        btn2 = view.findViewById(R.id.btn_fl2);
        btn3 = view.findViewById(R.id.btn_fl3);
        btn4 = view.findViewById(R.id.btn_fl4);

        //txt color
        txt0 = view.findViewById(R.id.textFloor_0);
        txt1 = view.findViewById(R.id.textFloor_1);
        txt2 = view.findViewById(R.id.textFloor_2);
        txt3 = view.findViewById(R.id.textFloor_3);
        txt4 = view.findViewById(R.id.textFloor_4);

        //setting map
        showMap();

        return view;
    }

    private void showMap(){
        floor_iamge.setImage(ImageSource.resource(R.drawable.fl2));
        btn2.setBackgroundResource(R.drawable.corners_bg_map);
        txt2.setTextColor(getResources().getColor(R.color.floor_select));

        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_iamge.setImage(ImageSource.resource(R.drawable.fl4));

                btn0.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn1.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn2.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn3.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn4.setBackgroundResource(R.drawable.corners_bg_map);

                txt0.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt1.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt2.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt3.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt4.setTextColor(getResources().getColor(R.color.floor_select));

            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_iamge.setImage(ImageSource.resource(R.drawable.fl3));

                btn0.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn1.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn2.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn3.setBackgroundResource(R.drawable.corners_bg_map);
                btn4.setBackgroundResource(R.drawable.corners_bg_map_unselected);

                txt0.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt1.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt2.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt3.setTextColor(getResources().getColor(R.color.floor_select));
                txt4.setTextColor(getResources().getColor(R.color.floor_unselect));

            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_iamge.setImage(ImageSource.resource(R.drawable.fl2));

                btn0.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn1.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn2.setBackgroundResource(R.drawable.corners_bg_map);
                btn3.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn4.setBackgroundResource(R.drawable.corners_bg_map_unselected);

                txt0.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt1.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt2.setTextColor(getResources().getColor(R.color.floor_select));
                txt3.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt4.setTextColor(getResources().getColor(R.color.floor_unselect));

            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_iamge.setImage(ImageSource.resource(R.drawable.fl1));

                btn0.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn1.setBackgroundResource(R.drawable.corners_bg_map);
                btn2.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn3.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn4.setBackgroundResource(R.drawable.corners_bg_map_unselected);

                txt0.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt1.setTextColor(getResources().getColor(R.color.floor_select));
                txt2.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt3.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt4.setTextColor(getResources().getColor(R.color.floor_unselect));
            }
        });
        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                floor_iamge.setImage(ImageSource.resource(R.drawable.fl0));

                btn0.setBackgroundResource(R.drawable.corners_bg_map);
                btn1.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn2.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn3.setBackgroundResource(R.drawable.corners_bg_map_unselected);
                btn4.setBackgroundResource(R.drawable.corners_bg_map_unselected);

                txt0.setTextColor(getResources().getColor(R.color.floor_select));
                txt1.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt2.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt3.setTextColor(getResources().getColor(R.color.floor_unselect));
                txt4.setTextColor(getResources().getColor(R.color.floor_unselect));

            }
        });
        
    }
}