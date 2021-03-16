package com.example.mireaapp.Frgaments.Schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;
import com.facebook.shimmer.ShimmerFrameLayout;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class ScheduleItemsAdapter extends RecyclerView.Adapter<ScheduleItemsAdapter.ScheduleItemsViewHolder> {

    private int itemsCount;
    private static int viewHolderCount;
    private List<ScheduleItem> scheduleItemsList;
    Context context;

    boolean isShimmer = true;
    int ShimmerNumber = 5;

    public ScheduleItemsAdapter(int numbersOfItems, List<ScheduleItem> scheduleItemsList, boolean isShimmer) {
        this.itemsCount = numbersOfItems;
        this.viewHolderCount = 0;
        this.scheduleItemsList = scheduleItemsList;
        this.isShimmer = isShimmer;
    }

    @Override
    public ScheduleItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        int scheduleItemsLayoutId = R.layout.layout_schedule_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(scheduleItemsLayoutId, parent, false);

        ScheduleItemsViewHolder viewHolder = new ScheduleItemsViewHolder(view);

        viewHolderCount++;

        return viewHolder;
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(ScheduleItemsViewHolder holder, int position) {
        ScheduleItem scheduleItem = scheduleItemsList.get(position);


        if (isShimmer) {
            holder.shimmerFrameLayout.startShimmer();
        } else {
            holder.shimmerFrameLayout.stopShimmer();
            holder.shimmerFrameLayout.setShimmer(null);

            //holder.containerAnim.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));

            holder.titleTextView.setBackground(null);
            holder.titleTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.titleTextView.setText(scheduleItem.getScheduleItemName());

            holder.cabinetTextView.setBackground(null);
            holder.cabinetTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.cabinetTextView.setText(scheduleItem.getCabinet().toUpperCase());

            holder.timeStartTextView.setBackground(null);
            holder.timeStartTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.timeStartTextView.setText(scheduleItem.getTimeStart());

            holder.timeEndTextView.setBackground(null);
            holder.timeEndTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.timeEndTextView.setText(scheduleItem.getTimeEnd());

            holder.typeTextView.setBackground(null);
            holder.typeTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.typeTextView.setText(scheduleItem.getType());

            holder.teacherTextView.setBackground(null);
            holder.teacherTextView.setAnimation(AnimationUtils.loadAnimation(context, R.anim.fade_transition_animation));
            holder.teacherTextView.setText(scheduleItem.getTeacher());
        }

        if (scheduleItem.getScheduleItemName() == "null"){
         holder.itemCard.setVisibility(View.INVISIBLE);
            holder.itemCard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 180));
        }
        else {
            if (scheduleItem.getType() == "null") {
                holder.typeTextView.setVisibility(View.INVISIBLE);
            }
            if (scheduleItem.getCabinet() == "null") {
                holder.cabinetTextView.setVisibility(View.INVISIBLE);
            }
            if (scheduleItem.getTeacher() == "null"){
                holder.teacherTextView.setVisibility(View.INVISIBLE);
            }

            if (scheduleItem.getType().equals("Практика")) {
                holder.leftBar.setBackground(ContextCompat.getDrawable(holder.leftBar.getContext(), R.drawable.gradient_schedule_animation_1));
                AnimationDrawable animationDrawable = (AnimationDrawable) holder.leftBar.getBackground();
                animationDrawable.setEnterFadeDuration(10);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                // holder.leftBar.getBackground().setColorFilter(ContextCompat.getColor(holder.leftBar.getContext(), R.color.dark_schedule_left_bar_practice), PorterDuff.Mode.SRC_ATOP);
            } else if (scheduleItem.getType().equals("Лекция")) {
                holder.leftBar.setBackground(ContextCompat.getDrawable(holder.leftBar.getContext(), R.drawable.gradient_schedule_animation_2));
                AnimationDrawable animationDrawable = (AnimationDrawable) holder.leftBar.getBackground();
                animationDrawable.setEnterFadeDuration(10);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                // holder.leftBar.getBackground().setColorFilter(ContextCompat.getColor(holder.leftBar.getContext(), R.color.dark_schedule_left_bar_lecture), PorterDuff.Mode.SRC_ATOP);
            } else if (scheduleItem.getType().equals("Лабораторная")) {
                holder.leftBar.setBackground(ContextCompat.getDrawable(holder.leftBar.getContext(), R.drawable.gradient_schedule_animation_3));
                AnimationDrawable animationDrawable = (AnimationDrawable) holder.leftBar.getBackground();
                animationDrawable.setEnterFadeDuration(10);
                animationDrawable.setExitFadeDuration(5000);
                animationDrawable.start();
                // holder.leftBar.getBackground().setColorFilter(ContextCompat.getColor(holder.leftBar.getContext(), R.color.dark_schedule_left_bar_lab), PorterDuff.Mode.SRC_ATOP);
            } else {
                holder.leftBar.getBackground().setColorFilter(ContextCompat.getColor(holder.leftBar.getContext(), R.color.dark_icon_tint_color_bg), PorterDuff.Mode.SRC_ATOP);
            }
        }
    }

    @Override
    public int getItemCount() {
        return isShimmer?ShimmerNumber:itemsCount;
    }

    class ScheduleItemsViewHolder extends RecyclerView.ViewHolder{

        private TextView cabinetTextView;
        private TextView typeTextView;
        private TextView titleTextView;
        private TextView timeStartTextView;
        private TextView timeEndTextView;
        private TextView teacherTextView;
        private FrameLayout leftBar;
        private LinearLayout itemCard;
        private ShimmerFrameLayout shimmerFrameLayout;

        public ScheduleItemsViewHolder(View itemView){
            super(itemView);
            timeStartTextView = itemView.findViewById(R.id.tv_schedule_item_time_start);
            timeEndTextView = itemView.findViewById(R.id.tv_schedule_item_time_end);
            cabinetTextView = itemView.findViewById(R.id.tv_schedule_item_cabinet);
            typeTextView = itemView.findViewById(R.id.tv_schedule_item_type);
            titleTextView = itemView.findViewById(R.id.tv_schedule_item_title);
            leftBar = itemView.findViewById(R.id.fl_schedule_item_left_bar);
            teacherTextView = itemView.findViewById(R.id.tv_schedule_item_teacher);
            itemCard = itemView.findViewById(R.id.schedule_item_card);
            shimmerFrameLayout = itemView.findViewById(R.id.shimmerFrameLayout_schedule);
        }
    }
}
