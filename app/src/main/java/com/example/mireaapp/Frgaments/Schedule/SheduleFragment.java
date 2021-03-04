package com.example.mireaapp.Frgaments.Schedule;

import android.app.AlertDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.util.Patterns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mireaapp.Frgaments.Notes.CreateNote;
import com.example.mireaapp.Frgaments.Notes.NotesDatabase;
import com.example.mireaapp.R;
import com.example.mireaapp.Util.CalendarManager;

import org.json.JSONArray;
import org.json.JSONException;
import org.w3c.dom.Text;

import java.io.IOException;
import java.security.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.zip.Inflater;

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

    private SharedPreferences preferences;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View currentView = inflater.inflate(R.layout.fragment_shedule, container, false);

        preferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );
        String selectedGroup = preferences.getString("selected_group", "");

        // диалог выбора группы
        AlertDialog.Builder builder = new AlertDialog.Builder(currentView.getContext());
        View scheduleSettingsView = inflater.inflate(R.layout.layout_schedule_settings, (ViewGroup) currentView.findViewById(R.id.layout_schedule_settings_container));
        builder.setView(scheduleSettingsView);

        AlertDialog scheduleSettingsDialog = builder.create();
        if (scheduleSettingsDialog.getWindow() != null) {
            scheduleSettingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }

        final EditText inputGroup = scheduleSettingsView.findViewById(R.id.et_input_schedule_group);
        inputGroup.requestFocus();

        scheduleSettingsView.findViewById(R.id.tv_apply_schedule_settings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        if (inputGroup.getText().toString().trim().isEmpty()) {
                            Toast.makeText(getActivity(), "Вы должны ввести группу!", Toast.LENGTH_SHORT).show();
                        } else if (!Pattern.compile("[А-Яа-я]{4}-[0-9]{2}-[0-9]{2}").matcher(inputGroup.getText().toString()).matches()) {
                            Toast.makeText(getActivity(), "Проверьте правильность введённой группы. Пример: ИКБО-25-20", Toast.LENGTH_SHORT).show();
                        } else {
                            downloadScheduleData(inputGroup.getText().toString());
                            if(!preferences.getString("selected_group", "").equals("")) {
                                try {
                                    initializeScheduleData(currentView, CalendarManager.getCurrentWeek(), CalendarManager.getCurrentDayOfWeek(), preferences.getString("selected_group", ""));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                            scheduleSettingsDialog.dismiss();
                        }
                    }
                });
            }
        });

        currentView.findViewById(R.id.schedule_settings_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scheduleSettingsDialog.show();
            }
        });

        if(selectedGroup.equals("")){
            scheduleSettingsDialog.show();
        }

        // инициализация списка расписания для текущей недели
        try {
            if(!preferences.getString("selected_group", "").equals(""))
                initializeScheduleData(currentView, CalendarManager.getCurrentWeek(), CalendarManager.getCurrentDayOfWeek(), preferences.getString("selected_group", ""));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // кнопка выбора недели
        ImageButton button = (ImageButton) currentView.findViewById(R.id.schedule_calendar_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWeekNumberSelectorDialog(currentView, inflater);
            }
        });


        return currentView;

    }

    void createWeekNumberSelectorDialog(View currentView, LayoutInflater inflater){
        AlertDialog.Builder builder = new AlertDialog.Builder(currentView.getContext());
        View calendarView = inflater.inflate(R.layout.layout_schedule_weeks, (ViewGroup) currentView.findViewById(R.id.schedule_week_selector_container));

        // LinearLayout с вертикальной ориентацией, в котором будут распологаться лайауты с горизонтальной ориентацией, в которых будут кнопки
        LinearLayout layout = (LinearLayout) calendarView.findViewById(R.id.ll_calendar_dynamic);

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
                    initializeScheduleData(currentView, (int)view.getTag(), 0, preferences.getString("selected_group", ""));
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

        builder.setView(calendarView);
        AlertDialog weeksDialog = builder.create();
        if (weeksDialog.getWindow() != null) {
            weeksDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        weeksDialog.show();
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

        // устанавливаем даты для кнопок выбора дня недели
        ArrayList<Integer> daysInWeek = CalendarManager.getDaysInWeek(selectedWeek);
        ((TextView)currentView.findViewById(R.id.tv_date_0)).setText(String.valueOf(daysInWeek.get(0)));
        ((TextView)currentView.findViewById(R.id.tv_date_1)).setText(String.valueOf(daysInWeek.get(1)));
        ((TextView)currentView.findViewById(R.id.tv_date_2)).setText(String.valueOf(daysInWeek.get(2)));
        ((TextView)currentView.findViewById(R.id.tv_date_3)).setText(String.valueOf(daysInWeek.get(3)));
        ((TextView)currentView.findViewById(R.id.tv_date_4)).setText(String.valueOf(daysInWeek.get(4)));
        ((TextView)currentView.findViewById(R.id.tv_date_5)).setText(String.valueOf(daysInWeek.get(5)));

        for(int i = 0; i < 6; i++){
            dayButtons.get(i).setTag(i);
            dayButtons.get(i).setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    setSelectedDayButton(currentView, (int) view.getTag());
                    initializeScheduleData(currentView, selectedWeek, (int) view.getTag(), preferences.getString("selected_group", ""));
                }
            });
        }
    }

    void downloadScheduleData(String group){
        Request request = new Request.Builder().url("https://mirea.ninja:500/schedule/all/" + group).build();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );
        //String selectedGroup = preferences.getString("selected_group", "");
        SharedPreferences.Editor pEditor = preferences.edit();


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
                        if (res.equals("None")){
                            Toast.makeText(getActivity(),"Ошибка! Такой группы нет.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Успешно установлена группа " + group, Toast.LENGTH_SHORT).show();

                            pEditor.putString("selected_group", group);
                            pEditor.commit();

                            Long timestamp = System.currentTimeMillis() / 1000;

                            Schedule schedule = new Schedule(group, res, timestamp, timestamp);
                            ScheduleDatabase.getInstance(getActivity().getApplicationContext()).scheduleDao().insertSchedule(schedule);
                        }
                    }
                });
            }

        });
    }

    void initializeScheduleData(View currentView, int week, int dayOfWeek, String group) {
        this.selectedWeek = week;
        this.selectedDayOfWeek = dayOfWeek;

        initializeDayButtons(currentView);
        setSelectedDayButton(currentView, dayOfWeek);

        this.scheduleItems = new ArrayList<ScheduleItem>();

        TextView weekTextView = currentView.findViewById(R.id.tv_schedule_current_week);
        TextView selectedDateTextView = currentView.findViewById(R.id.tv_schedule_current_date);

        // чётность недели
        String even;
        if (week % 2 == 0)
            even = "чётная";
        else
            even = "нечётная";

        // Текст для TextView, в котором номер выбранной недели
        String weekText = String.valueOf(week) + " неделя (" + even + ")";
        // Текст для TextView, в котором выбранная дата (формат: День недели, число + месяц. Пример: "Понедельник, 1 марта")
        String dateText = CalendarManager.getTextDayOfWeek(dayOfWeek) + ", " + String.valueOf(CalendarManager.getDaysInWeek(week).get(dayOfWeek)) + " " + CalendarManager.getMonthStringByWeek(week);

        // Устанавливаем текст для выбранных дат
        weekTextView.setText(weekText);
        selectedDateTextView.setText(dateText);

        List<Schedule> schedule = ScheduleDatabase.getInstance(getActivity().getApplicationContext()).scheduleDao().getScheduleByGroup(group);
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

                itemsAdapter = new ScheduleItemsAdapter(scheduleItems.size(), scheduleItems);
                rvAllScheduleItems.setAdapter(itemsAdapter);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}