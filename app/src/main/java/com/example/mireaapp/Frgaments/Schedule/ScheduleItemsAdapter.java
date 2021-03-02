package com.example.mireaapp.Frgaments.Schedule;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

import java.util.List;

public class ScheduleItemsAdapter extends RecyclerView.Adapter<ScheduleItemsAdapter.ScheduleItemsViewHolder> {
    private int itemsCount;
    private static int viewHolderCount;
    private List<ScheduleItem> scheduleItemsList;

    public ScheduleItemsAdapter(int numbersOfItems, List<ScheduleItem> scheduleItemsList) {
        this.itemsCount = numbersOfItems;
        this.viewHolderCount = 0;
        this.scheduleItemsList = scheduleItemsList;
    }

    @Override
    public ScheduleItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
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

        holder.titleTextView.setText(scheduleItem.getScheduleItemName());
        holder.cabinetTextView.setText(scheduleItem.getCabinet());
        holder.timeStartTextView.setText(scheduleItem.getTimeStart());
        holder.timeEndTextView.setText(scheduleItem.getTimeEnd());
        holder.typeTextView.setText(scheduleItem.getType());

        GradientDrawable shape = (GradientDrawable) holder.leftBar.getBackground();

        GradientDrawable drawable = (GradientDrawable) holder.leftBar.getResources().getDrawable(R.drawable.schedule_item_left_bar);
        drawable.mutate();

        if (scheduleItem.getScheduleItemName() == "null"){
         holder.itemCard.setVisibility(View.INVISIBLE);
            holder.itemCard.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, 180));
            //setLayoutParams(new LinearLayout.LayoutParams(1080, 400))
        }

        if(scheduleItem.getType().equals("Практика")){
            drawable.setColor(R.color.dark_schedule_left_bar_practice);
            //shape.setColor(R.color.dark_schedule_left_bar_practice);
        }
        else if(scheduleItem.getType().equals("Лекция")){
            drawable.setColor(R.color.dark_schedule_left_bar_lecture);
        }
        else if(scheduleItem.getType().equals("Лабораторная")){
            drawable.setColor(R.color.dark_schedule_left_bar_lab);
        }
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    class ScheduleItemsViewHolder extends RecyclerView.ViewHolder{
        private TextView cabinetTextView;
        private TextView typeTextView;
        private TextView titleTextView;
        private TextView timeStartTextView;
        private TextView timeEndTextView;
        private FrameLayout leftBar;
        private LinearLayout itemCard;

        public ScheduleItemsViewHolder(View itemView){
            super(itemView);
            timeStartTextView = itemView.findViewById(R.id.tv_schedule_item_time_start);
            timeEndTextView = itemView.findViewById(R.id.tv_schedule_item_time_end);
            cabinetTextView = itemView.findViewById(R.id.tv_schedule_item_cabinet);
            typeTextView = itemView.findViewById(R.id.tv_schedule_item_type);
            titleTextView = itemView.findViewById(R.id.tv_schedule_item_title);
            leftBar = itemView.findViewById(R.id.fl_schedule_item_left_bar);
            itemCard = itemView.findViewById(R.id.schedule_item_card);
        }
    }
}
