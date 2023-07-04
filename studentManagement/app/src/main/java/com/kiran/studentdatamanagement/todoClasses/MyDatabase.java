package com.kiran.studentdatamanagement.todoClasses;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {StudentData.class},version = 1)
public abstract class MyDatabase extends RoomDatabase {

    public abstract MyDao myDao();

    private static MyDatabase database;

    public static synchronized MyDatabase getInstance(Context context) {
        if (database == null) {
            database = Room.databaseBuilder(context.getApplicationContext(),
                    MyDatabase.class, "student_db").build();
        }
        return database;
    }
}