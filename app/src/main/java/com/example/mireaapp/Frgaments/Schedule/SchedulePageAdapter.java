package com.example.mireaapp.Frgaments.Schedule;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.mireaapp.R;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

public class SchedulePageAdapter extends RecyclerView.Adapter<SchedulePageAdapter.SchedulePagesViewHolder> {
    private int week;
    private String group;

    private RecyclerView rvAllScheduleItems;
    private ScheduleItemsAdapter itemsAdapter;
    private ArrayList<ScheduleItem> scheduleItems;

    public SchedulePageAdapter(int week, String group) {
        this.week = week;
        this.group = group;
    }

    @NonNull
    @Override
    public SchedulePagesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View view = inflater.inflate(R.layout.fragment_schedule_page, parent, false);

        SchedulePagesViewHolder viewHolder = new SchedulePagesViewHolder(view, this.week, this.group);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchedulePagesViewHolder holder, int position) {
        initializeScheduleData(holder.getItemView(), holder.getWeek(), position, holder.getGroup(), holder.getItemView().getContext());
    }

    @Override
    public int getItemCount() {
        return 6;
    }

    private void initializeScheduleData(View currentView, int week, int dayOfWeek, String group, Context activity) {
        this.scheduleItems = new ArrayList<ScheduleItem>();

        List<Schedule> schedule = ScheduleDatabase.getInstance(activity).scheduleDao().getScheduleByGroup(group);
        if (schedule.size() > 0){
            try {
                JSONArray jArray = new JSONArray(schedule.get(0).getJsonData());

                for (int subjectIndex = 0; subjectIndex < 6; subjectIndex++){
                    String subject = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("subject").getString(subjectIndex);
                    String cab = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("cab").getString(subjectIndex);
                    String type = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("type").getString(subjectIndex);
                    String teacher = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("teacher").getString(subjectIndex);
                    scheduleItems.add(new ScheduleItem(subject, cab, type, ScheduleItem.getTimeStart(subjectIndex), ScheduleItem.getTimeEnd(subjectIndex), teacher));
                }

                rvAllScheduleItems = (RecyclerView) currentView.findViewById(R.id.rv_all_schedul_items);

                itemsAdapter = new ScheduleItemsAdapter(scheduleItems.size(), scheduleItems, false);
                rvAllScheduleItems.setAdapter(itemsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    class SchedulePagesViewHolder extends RecyclerView.ViewHolder{
        private View itemView;
        private int week;
        private String group;

        public SchedulePagesViewHolder(View itemView, int week, String group){
            super(itemView);
            this.itemView = itemView;
            this.week = week;
            this.group = group;
        }

        public View getItemView() {
            return itemView;
        }

        public int getWeek() {
            return week;
        }

        public String getGroup() {
            return group;
        }
    }
}
