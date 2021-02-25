package com.example.mireaapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.R;

public class ScheduleItemsAdapter extends RecyclerView.Adapter<ScheduleItemsAdapter.ScheduleItemsViewHolder> {
    private int itemsCount;
    private static int viewHolderCount;

    public ScheduleItemsAdapter(int numbersOfItems) {
        itemsCount = numbersOfItems;
        viewHolderCount = 0;
    }

    @Override
    public ScheduleItemsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int scheduleItemsLayoutId = R.layout.layout_schedule_item;

        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(scheduleItemsLayoutId, parent, false);

        ScheduleItemsViewHolder viewHolder = new ScheduleItemsViewHolder(view);
        viewHolder.titleTextView.setText("View holder test!!!");

        viewHolderCount++;

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ScheduleItemsViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return itemsCount;
    }

    class ScheduleItemsViewHolder extends RecyclerView.ViewHolder{
        private TextView cabinetTextView;
        private TextView typeTextView;
        private TextView titleTextView;

        public ScheduleItemsViewHolder(View itemView){
            super(itemView);

            cabinetTextView = itemView.findViewById(R.id.tv_schedule_item_cabinet);
            typeTextView = itemView.findViewById(R.id.tv_schedule_item_type);
            titleTextView = itemView.findViewById(R.id.tv_schedule_item_title);
        }

        void bind(int listIndex){

        }
    }
}
