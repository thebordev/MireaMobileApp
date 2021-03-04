package com.example.mireaapp.Frgaments.Schedule;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ScheduleDao {
    @Query("SELECT * FROM schedules WHERE `group` = :group")
    List<Schedule> getScheduleByGroup(String group);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSchedule(Schedule schedule);

    @Delete
    void deleteSchedule(Schedule schedule);
}
