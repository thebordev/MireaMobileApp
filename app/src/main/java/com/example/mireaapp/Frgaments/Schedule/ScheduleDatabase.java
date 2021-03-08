package com.example.mireaapp.Frgaments.Schedule;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = Schedule.class, version = 2, exportSchema = false)
public abstract class ScheduleDatabase extends RoomDatabase {

    private static ScheduleDatabase scheduleDatabase;

    public static synchronized ScheduleDatabase getInstance(Context context) {
        if (scheduleDatabase == null) {
            scheduleDatabase = Room.databaseBuilder(
                    context,
                    ScheduleDatabase.class,
                    "schedules.db"
            ).allowMainThreadQueries().fallbackToDestructiveMigration().build();
        }
        return scheduleDatabase;
    }
    public abstract ScheduleDao scheduleDao();
}
