package com.example.roomdbwithlivedata;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class} , version = 1 , exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {

    public abstract StudentDao myDao();

    static StudentDataBase dataBase;

    public static synchronized StudentDataBase getDataBase(Context context){
        if (dataBase == null){
            dataBase = Room.databaseBuilder(context,
                    StudentDataBase.class,"MyDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();
        }
        return dataBase;
    }





}
