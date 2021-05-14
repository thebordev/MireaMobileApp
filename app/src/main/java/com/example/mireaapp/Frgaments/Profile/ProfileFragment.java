package com.example.mireaapp.Frgaments.Profile;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mireaapp.Frgaments.Profile.teacher.Teacher;
import com.example.mireaapp.Frgaments.Profile.teacher.TeacherAdapter;
import com.example.mireaapp.Frgaments.Schedule.Data.Schedule;
import com.example.mireaapp.Frgaments.Schedule.Data.ScheduleDatabase;
import com.example.mireaapp.R;
import com.example.mireaapp.Utils.ScheduleApi;
import com.example.mireaapp.Utils.Credentials;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Pattern;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class ProfileFragment extends Fragment {

    private LinearLayout gradientLayout, feedbackBtn, groupBtn, teacherBtn, settingBtn;
    private SharedPreferences preferences;

    private TeacherAdapter teacherAdapter;
    ArrayList<Teacher> models = new ArrayList<>();
    private ArrayList<String> groupList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        gradientLayout = view.findViewById(R.id.gradientHat_profile);

        feedbackBtn = view.findViewById(R.id.feedback_profile);
        teacherBtn = view.findViewById(R.id.find_teacherBtn);
        groupBtn = view.findViewById(R.id.group_select_button);
        settingBtn = view.findViewById(R.id.setting_profile);

        preferences = getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );

        feedback(); //кнопка обратной связи
        teachers(); //кнопка найти преподавателя
        groupSelect(view); //кнопка выбора группы
        gradientHatInit(); // Анимация градиента в боксе профиля
        showListGroup();

        return view;
    }

    private void groupSelect(View view) {
        setSelectedGroupToTextView(view);
        groupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showGroupSelectDialog();
            }
        });
    }
    private void teachers() {
        teacherBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RecyclerView rvTeachers;
                teacherAdapter = new TeacherAdapter(models, getContext());

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getContext());
                bottomSheetDialog.setContentView(R.layout.layout_find_teacher);

                rvTeachers = bottomSheetDialog.findViewById(R.id.teacherList);

                rvTeachers.setLayoutManager(new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false));
                rvTeachers.hasFixedSize();
                rvTeachers.setAdapter(teacherAdapter);

                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));
                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));
                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));
                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));
                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));
                models.add(new Teacher("Зуев А.С.", "доцент", "9:00", "10:30", "Объектно-ориентированное программирование", "Д"));

                teacherAdapter.notifyDataSetChanged();
                bottomSheetDialog.show();
            }
        });
    }
    private void feedback(){
        feedbackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LinearLayout linTelegram, linEmail;

                BottomSheetDialog bottomSheetDialog = new BottomSheetDialog(getActivity());
                bottomSheetDialog.setContentView(R.layout.layout_helper);

                linTelegram = bottomSheetDialog.findViewById(R.id.send_message_to_telegram);
                linTelegram.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/thebordevs"));
                        startActivity(telegram);
                    }
                });

                linEmail = bottomSheetDialog.findViewById(R.id.send_message_to_mail);
                linEmail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent mail = new Intent(Intent.ACTION_SEND);
                        mail.setType("plain/text");
                        mail.putExtra(Intent.EXTRA_EMAIL, new String[]{"theboringdevelopers@gmail.com"});
                        mail.putExtra(Intent.EXTRA_SUBJECT, "MireaApp");
                        startActivity(Intent.createChooser(mail, ""));
                    }
                });

                bottomSheetDialog.show();
            }
        });

    }
    private void gradientHatInit() {
        AnimationDrawable animationDrawable = (AnimationDrawable) gradientLayout.getBackground();
        animationDrawable.setEnterFadeDuration(10);
        animationDrawable.setExitFadeDuration(5000);
        animationDrawable.start();
    }
    private void showListGroup() {
        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                View scheduleSettingsView =
//                        getLayoutInflater().
//                                inflate(R.layout.ad_group_selection, (ViewGroup) getView().
//                                        findViewById(R.id.layout_schedule_settings_container_ad));
//                builder.setView(scheduleSettingsView);
//
//                AlertDialog scheduleSettingsDialog = builder.create();


                if (InternetConnection.checkConnection(getActivity())) {
                    GetRetrofitV1();

                } else {
                    Toast.makeText(getActivity(), "No internet!", Toast.LENGTH_SHORT).show();
                }




                //scheduleSettingsDialog.show();
            }
        });
    }
    private void setSelectedGroupToTextView(View view){
        TextView currentGroupTv = (TextView)view.findViewById(R.id.textGroup);
        String group = preferences.getString("selected_group", "");
        if(group.equals("")){
            currentGroupTv.setText("не установлена");
        }
        else {
            currentGroupTv.setText(group);
        }
    }
    private void showGroupSelectDialog(){
        // диалог выбора группы
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        View scheduleSettingsView = getLayoutInflater().inflate(R.layout.layout_schedule_settings, (ViewGroup) getView().findViewById(R.id.layout_schedule_settings_container));
        builder.setView(scheduleSettingsView);
        AlertDialog scheduleSettingsDialog = builder.create();
        if (scheduleSettingsDialog.getWindow() != null) {
            scheduleSettingsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        }
        final EditText inputGroup = scheduleSettingsView.findViewById(R.id.et_input_schedule_group);
        inputGroup.requestFocus();
        EditText groupEditText = (EditText) scheduleSettingsView.findViewById(R.id.et_input_schedule_group);

        // методы вызываются рекурсивно, поэтому без повторяющегося кода не обойтись, иначе приложение зависнет
        groupEditText.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after)
            {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s != null && s.length() > 0) {
                    String groupText = s.toString();
                    if (groupText.length() <= 4) {
                        String lastChar = groupText.substring(groupText.length() - 1);
                        if (!lastChar.matches("[а-яА-Я]")) {
                            groupText = groupText.substring(0, groupText.length() - 1);
                            groupEditText.setText(groupText);
                            groupEditText.setSelection(groupEditText.getText().length());
                        }
                    }
                    if(before < s.length() && start != 4 && start != 7){
                        if ((groupText.length() == 4 || groupText.length() == 7)) {
                            groupText += "-";
                            groupEditText.setText(groupText);
                            groupEditText.setSelection(groupEditText.getText().length());
                        }
                        else if ((groupText.length() == 5 || groupText.length() == 8)) {
                            StringBuffer sb = new StringBuffer(groupText);
                            if(groupText.length() == 5) {
                                if(groupText.charAt(4) != '-')
                                    sb.insert(4, "-");
                            }
                            else if (groupText.length() == 8) {
                                if(groupText.charAt(7) != '-')
                                    sb.insert(7, "-");
                            }
                            groupText = sb.toString();
                            groupEditText.setText(groupText);
                            groupEditText.setSelection(groupEditText.getText().length());
                        }
                    }
                    else if(before > s.length()){
                        if ((groupText.length() == 5 || groupText.length() == 8)) {
                            groupText = groupText.substring(0, groupText.length() - 1);
                            groupEditText.setText(groupText);
                            groupEditText.setSelection(groupEditText.getText().length());
                        }
                    }

                    if(s.length() > 10){
                        groupText = groupText.substring(0, groupText.length() - 1);
                        groupEditText.setText(groupText);
                        groupEditText.setSelection(groupEditText.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

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
//                            if(!preferences.getString("selected_group", "").equals("")) {
//                                //initializeScheduleData(currentView, CalendarManager.getCurrentWeek(), CalendarManager.getCurrentDayOfWeek(), preferences.getString("selected_group", ""));
//                            }
                            scheduleSettingsDialog.dismiss();
                        }
                    }
                });
            }
        });

        scheduleSettingsDialog.show();
    }
    private void downloadScheduleData(String group){
        Log.d("TAG_API", group.toUpperCase());
        String url = "http://167.99.39.136:5000/api/schedule/" + group.toUpperCase() + "/all_weeks";
        Request request = new Request.Builder().url(url).build();

        SharedPreferences preferences = this.getActivity().getSharedPreferences("settings", Context.MODE_PRIVATE );
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
                            Toast.makeText(getActivity(), "Ошибка! Такой группы нет.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Toast.makeText(getActivity(),"Успешно установлена группа " + group, Toast.LENGTH_SHORT).show();

                            pEditor.putString("selected_group", group);
                            pEditor.commit();

                            setSelectedGroupToTextView(getView());

                            Long timestamp = System.currentTimeMillis() / 1000;

                            Schedule schedule = new Schedule(group, res, timestamp, timestamp);
                            ScheduleDatabase.getInstance(getActivity().getApplicationContext()).scheduleDao().insertSchedule(schedule);
                        }
                    }
                });
            }
        });
    }
    private void GetRetrofitV1() {
        final ProgressDialog dialog;

        Toast.makeText(getActivity(), "Test", Toast.LENGTH_SHORT).show();

        dialog = new ProgressDialog(getContext());
        dialog.setTitle("Warning!");
        dialog.setMessage("Please, wait");
        dialog.show();

        ScheduleApi api = Credentials.getApiService();

        retrofit2.Call<GroupsList> call = api.groupJson();
        call.enqueue(new retrofit2.Callback<GroupsList>() {
            @Override
            public void onResponse(retrofit2.Call<GroupsList> call, retrofit2.Response<GroupsList> response) {
                dialog.dismiss();

                if (response.isSuccessful()) {
                    assert response.body() != null;
                    groupList = response.body().getGroups();
                    Log.d("API", groupList.toString());
                } else {
                    Toast.makeText(getContext(), "Bad!", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(retrofit2.Call<GroupsList> call, Throwable t) {
                Log.d("API", "FAILED!");
                Log.d("API", t.getMessage());
                dialog.dismiss();
            }
        });
    }
}