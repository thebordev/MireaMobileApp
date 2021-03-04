package com.example.mireaapp.Frgaments.Schedule;

import android.app.AlertDialog;
import android.graphics.Color;
import android.graphics.PorterDuff;
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
import android.widget.TextView;

import com.example.mireaapp.R;
import com.example.mireaapp.Util.CalendarManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

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
    private ArrayList<LinearLayout> dayButtons;

    private int selectedWeek = 0;
    private int selectedDayOfWeek = 0;

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

        // кнопка выбора недели
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

                // цикл отображения кнопок выбора недели
                for (int i = 1; i <= CalendarManager.MAX_WEEKS_IN_SEMESTER; i++) {
                    // создаём новую кнопку с id = i
                    Button button = new Button(currentView.getContext());
                    button.setText(String.valueOf(i));

                    button.setTag(i);

                    buttonsLayout.addView(button);

                    button.setOnClickListener(new View.OnClickListener() {
                        public void onClick(View view) {
                            initializeScheduleData(currentView, (int)view.getTag(), 0);
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

    void setSelectedDayButton(View view, int dayOfWeek){
        if (view.getTag() == null){
            for (int i = 0; i < 6; i++) {
                if (i == dayOfWeek) {
                    view.findViewWithTag(dayOfWeek).getBackground().setColorFilter(Color.parseColor("#9a9898"), PorterDuff.Mode.SRC_ATOP);
                } else {
                    view.findViewWithTag(i).getBackground().setColorFilter(Color.parseColor("#cbcbcb"), PorterDuff.Mode.SRC_ATOP);
                }
            }

        }
    }

    void initializeDayButtons(View currentView){
        dayButtons = new ArrayList<LinearLayout>();

        dayButtons.add(currentView.findViewById(R.id.ll_date_0));
        dayButtons.add(currentView.findViewById(R.id.ll_date_1));
        dayButtons.add(currentView.findViewById(R.id.ll_date_2));
        dayButtons.add(currentView.findViewById(R.id.ll_date_3));
        dayButtons.add(currentView.findViewById(R.id.ll_date_4));
        dayButtons.add(currentView.findViewById(R.id.ll_date_5));


        for(int i = 0; i < 6; i++){
            dayButtons.get(i).setTag(i);
            dayButtons.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    setSelectedDayButton(currentView, (int) view.getTag());
                    initializeScheduleData(currentView, selectedWeek, (int) view.getTag());
                }
            });
        }
    }

    void initializeScheduleData(View currentView, int week, int dayOfWeek){
        Request request = new Request.Builder().url("https://mirea.ninja:500/schedule/all/ИКБО-25-20").build();

        this.selectedWeek = week;
        this.selectedDayOfWeek = dayOfWeek;

        initializeDayButtons(currentView);
        setSelectedDayButton(currentView, dayOfWeek);

        this.scheduleItems = new ArrayList<ScheduleItem>();

        TextView weekTextView = currentView.findViewById(R.id.tv_schedule_current_week);
        TextView selectedDateTextView = currentView.findViewById(R.id.tv_schedule_current_date);

        String even;
        if (week % 2 == 0)
            even = "чётная";
        else
            even = "нечётная";

        String weekText = String.valueOf(week) + " неделя (" + even + ")";
        weekTextView.setText(weekText);
        selectedDateTextView.setText(CalendarManager.getCurrentTextDayOfWeek());

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