package com.example.mireaapp.Frgaments.Schedule.Adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mireaapp.Frgaments.Schedule.Models.ScheduleItem;
import com.example.mireaapp.Frgaments.Schedule.Data.Schedule;
import com.example.mireaapp.Frgaments.Schedule.Data.ScheduleDatabase;
import com.example.mireaapp.R;
import com.example.mireaapp.Utils.CalendarManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
        week = week - 1;
        Log.d("myTag", String.valueOf(week));
        List<Schedule> schedule = ScheduleDatabase.getInstance(activity).scheduleDao().getScheduleByGroup(group);
        String dayOfWeekText = CalendarManager.getEnTextDayOfWeek(dayOfWeek);
        if (schedule.size() > 0){
            try {
                JSONArray jArray = new JSONArray(schedule.get(0).getJsonData());
                JSONArray jArrayOfWeek = jArray.getJSONObject(week).getJSONArray(dayOfWeekText);
                for (int subjectIndex = 0; subjectIndex < 6; subjectIndex++){
                    JSONObject jLesson = jArrayOfWeek.getJSONObject(subjectIndex);
                    if(!jLesson.isNull("lesson")){
                        jLesson = jLesson.getJSONObject("lesson");
                        String subject = jLesson.getString("name");
                        String cab = jLesson.getString("classRoom");
                        String type = jLesson.getString("type").replace("пр", "Практика").replace("лк", "Лекция").replace("лаб", "Лабораторная");
                        String teacher = jLesson.getString("teacher");
                        scheduleItems.add(new ScheduleItem(subject, cab, type, ScheduleItem.getTimeStart(subjectIndex), ScheduleItem.getTimeEnd(subjectIndex), teacher));
                    }
                    else{
                        continue;
                    }
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
