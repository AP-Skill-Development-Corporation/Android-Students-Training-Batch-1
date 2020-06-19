package com.example.roomdbwithlivedata;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Student.class} , version = 1 , exportSchema = false)
public abstract class StudentDataBase extends RoomDatabase {

    public abstract StudentDao myDao();

}
