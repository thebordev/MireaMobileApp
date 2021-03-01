package com.example.mireaapp.Frgaments;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.mireaapp.R;
import com.example.mireaapp.ScheduleItem;
import com.example.mireaapp.Util.CalendarManager;
import com.example.mireaapp.adapters.ScheduleItemsAdapter;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SheduleFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SheduleFragment extends Fragment {
    //Создаем список вьюх которые будут создаваться
    private RecyclerView rvAllScheduleItems;
    private ScheduleItemsAdapter itemsAdapter;
    private ArrayList<ScheduleItem> scheduleItems;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View currentView = inflater.inflate(R.layout.fragment_shedule, container, false);

        // инициализация списка расписания
        try {
            initializeScheduleData(currentView, CalendarManager.getCurrentWeek(), CalendarManager.getCurrentDayOfWeek());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        ImageButton button = (ImageButton) currentView.findViewById(R.id.schedule_calendar_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(currentView.getContext());

                View calendarView = inflater.inflate(R.layout.layout_schedule_calendar, null);
                // LinearLayout с вертикальной ориентацией, в котором будут распологаться лайауты с горизонтальной ориентацией, в которых будут кнопки
                LinearLayout layout = (LinearLayout) calendarView.findViewById(R.id.ll_calendar_dynamic);

                dialog.setTitle("Выберите неделю");

                // LinearLayout buttonsLayout нужен для переноса кнопок на новую строку, чтобы они уместились
                LinearLayout buttonsLayout = new LinearLayout(currentView.getContext());
                buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.HORIZONTAL));
                layout.addView(buttonsLayout);

                for (int i = 1; i <= CalendarManager.MAX_WEEKS_IN_SEMESTER; i++) {
                    // создаём новую кнопку с id = i
                    Button button = new Button(currentView.getContext());
                    button.setText(String.valueOf(i));
                    button.setId(i);

                    //final int id_ = button.getId();
                    button.setId(i);

                    buttonsLayout.addView(button);

                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            initializeScheduleData(currentView, view.getId(), 0);
                        }
                    });

                    // упаковываем кнопки в лайауты по 4 кнопки в каждом
                    if(i % 4 == 0){
                        buttonsLayout = new LinearLayout(currentView.getContext());
                        buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.HORIZONTAL));
                        layout.addView(buttonsLayout);
                    }
                    else if(i == CalendarManager.MAX_WEEKS_IN_SEMESTER){
                        //layout.addView(buttonsLayout);
                    }
                }

                dialog.setView(calendarView);
                dialog.create();
                dialog.show();
            }
        });


        return currentView;

    }


    void initializeScheduleData(View currentView, int week, int dayOfWeek){
        Request request = new Request.Builder().url("http://10.0.2.2:5000/schedule/all/ИКБО-25-20").build();
        scheduleItems = new ArrayList<ScheduleItem>();

        new OkHttpClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(final Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, final Response response) throws IOException {
                String res = response.body().string();
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            JSONArray jArray = new JSONArray(res);

                            for (int subjectIndex = 0; subjectIndex < 6; subjectIndex++){
                                String subject = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("subject").getString(subjectIndex);
                                String cab = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("cab").getString(subjectIndex);
                                String type = jArray.getJSONArray(week).getJSONObject(dayOfWeek).getJSONArray("type").getString(subjectIndex);
                                scheduleItems.add(new ScheduleItem(subject, cab, type, ScheduleItem.getTimeStart(subjectIndex), ScheduleItem.getTimeEnd(subjectIndex)));
                            }

                            rvAllScheduleItems = (RecyclerView) currentView.findViewById(R.id.rv_all_schedul_items);

                            itemsAdapter = new ScheduleItemsAdapter(scheduleItems.size(), scheduleItems);
                            rvAllScheduleItems.setAdapter(itemsAdapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        });
    }
}