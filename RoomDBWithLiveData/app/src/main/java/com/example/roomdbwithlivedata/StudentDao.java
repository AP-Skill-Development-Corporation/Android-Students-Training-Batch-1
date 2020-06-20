package com.example.roomdbwithlivedata;


import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface StudentDao {

    @Insert
    public void insert(Student student);

    @Delete
    public void delete(Student student);

    @Query("SELECT * FROM StudentInfo")
    LiveData<List<Student>> readData();

}
