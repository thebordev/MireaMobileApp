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
import androidx.fragment.app.FragmentActivity;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mireaapp.R;
import com.example.mireaapp.Util.CalendarManager;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.ParseException;
import java.util.ArrayList;



public class ScheduleFragment extends Fragment {

    NavHostFragment navHostFragment;
    BottomNavigationView bottomNavigationView;
    private View currentView;
    private SchedulePageAdapter pagerAdapter;
    private ViewPager2 schedulePager;

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
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.fragment_schedule, container, false);
        currentView = view;
        preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );

        try {
            selectedWeek = CalendarManager.getCurrentWeek();
            selectedDayOfWeek = CalendarManager.getCurrentDayOfWeek();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        // кнопка выбора недели
        ImageButton button = (ImageButton) view.findViewById(R.id.schedule_calendar_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createWeekNumberSelectorDialog();
            }
        });

        createSchedulePages();
        // если сегодня воскресенье, то переносимся на понедельник следующей недели
        if (selectedDayOfWeek == 6){
            selectedDayOfWeek = 0;
            selectedWeek++;
        }
        schedulePager.setCurrentItem(selectedDayOfWeek);
        setSelectedDayButton(selectedDayOfWeek);
        return view;
    }

    void setSelectedDayButton(int dayOfWeek){
        for (int i = 0; i < 6; i++) {
            if (i == dayOfWeek) {
                dayButtons.get(dayOfWeek).getBackground().setColorFilter(Color.parseColor("#0881ed"), PorterDuff.Mode.SRC_ATOP);

                for (int j = 0; j < dayButtons.get(dayOfWeek).getChildCount(); j++) {
                    View v = dayButtons.get(dayOfWeek).getChildAt(j);
                    if (v instanceof TextView) {
                        ((TextView) v).setTextColor(Color.WHITE);
                    }
                }
            } else {
                dayButtons.get(i).getBackground().setColorFilter(Color.parseColor("#9a9898"), PorterDuff.Mode.SRC_ATOP);
                for (int j = 0; j < dayButtons.get(dayOfWeek).getChildCount(); j++) {
                    View v = dayButtons.get(i).getChildAt(j);
                    if (v instanceof TextView) {
                        ((TextView) v).setTextColor(Color.DKGRAY);
                    }
                }
            }
        }
    }

    void initializeDayButtons(){
        dayButtons = new ArrayList<LinearLayout>();

        dayButtons.add(currentView.findViewById(R.id.ll_date_0));
        dayButtons.add(currentView.findViewById(R.id.ll_date_1));
        dayButtons.add(currentView.findViewById(R.id.ll_date_2));
        dayButtons.add(currentView.findViewById(R.id.ll_date_3));
        dayButtons.add(currentView.findViewById(R.id.ll_date_4));
        dayButtons.add(currentView.findViewById(R.id.ll_date_5));

        //устанавливаем даты для кнопок выбора дня недели
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
                    selectedDayOfWeek = (int) view.getTag();
                    schedulePager.setCurrentItem((int) view.getTag()+1);
                    setSelectedDayButton(selectedDayOfWeek);
                }
            });
        }
    }

    void createWeekNumberSelectorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View calendarView = getLayoutInflater().inflate(R.layout.layout_schedule_weeks, (ViewGroup) getView().findViewById(R.id.schedule_week_selector_container));

        // LinearLayout с вертикальной ориентацией, в котором будут распологаться лайауты с горизонтальной ориентацией, в которых будут кнопки
        LinearLayout layout = (LinearLayout) calendarView.findViewById(R.id.ll_calendar_dynamic);

        // LinearLayout buttonsLayout нужен для переноса кнопок на новую строку, чтобы они уместились
        LinearLayout buttonsLayout = new LinearLayout(getContext());
        buttonsLayout.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, Constraints.LayoutParams.HORIZONTAL));
        layout.addView(buttonsLayout);

        // цикл отображения кнопок выбора недели
        for (int i = 1; i <= CalendarManager.MAX_WEEKS_IN_SEMESTER; i++) {
            // создаём новую кнопку с id = i
            Button button = new Button(getContext());
            button.setText(String.valueOf(i));

            button.setTag(i);

            buttonsLayout.addView(button);

            button.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    selectedWeek = (int)view.getTag();
                    createSchedulePages();
                }
            });

            // упаковываем кнопки в лайауты по 4 кнопки в каждом
            if(i % 4 == 0){
                buttonsLayout = new LinearLayout(getContext());
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

    private void createSchedulePages(){
        schedulePager = (ViewPager2)currentView.findViewById(R.id.schedule_pager);
        pagerAdapter = new SchedulePageAdapter(selectedWeek, preferences.getString("selected_group", ""));

        initializeDayButtons();

        String group = preferences.getString("selected_group", "");

        if (!group.equals("")) {
            schedulePager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
                @Override
                public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                    super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                }

                @Override
                public void onPageSelected(int position) {
                    super.onPageSelected(position);
                    setSelectedDayButton(position);

                    TextView weekTextView = currentView.findViewById(R.id.tv_schedule_current_week);
                    TextView selectedDateTextView = currentView.findViewById(R.id.tv_schedule_current_date);

                    // чётность недели
                    String even;
                    if (selectedWeek % 2 == 0)
                        even = "чётная";
                    else
                        even = "нечётная";

                    // Текст для TextView, в котором номер выбранной недели
                    String weekText = String.valueOf(selectedWeek) + " неделя (" + even + ")";
                    // Текст для TextView, в котором выбранная дата (формат: День недели, число + месяц. Пример: "Понедельник, 1 марта")
                    String dateText = CalendarManager.getTextDayOfWeek(position) + ", " + String.valueOf(CalendarManager.getDaysInWeek(selectedWeek).get(position)) + " " + CalendarManager.getMonthStringByWeek(selectedWeek);

                    // Устанавливаем текст для выбранных дат
                    weekTextView.setText(weekText);
                    selectedDateTextView.setText(dateText);
                }

                @Override
                public void onPageScrollStateChanged(int state) {
                    super.onPageScrollStateChanged(state);
                }
            });
            // инициализация списка расписания для текущей недели
            schedulePager.setAdapter(pagerAdapter);
        }
    }
}