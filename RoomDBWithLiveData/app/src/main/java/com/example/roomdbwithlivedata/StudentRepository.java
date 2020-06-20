package com.example.roomdbwithlivedata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class StudentRepository {

    StudentDataBase studentDataBase;
    LiveData<List<Student>> read;

    public StudentRepository(Application application) {
        studentDataBase=StudentDataBase.getDataBase(application);
        read = studentDataBase.myDao().readData();
    }

    public void insertData(Student student){
        new InsertTask().execute(student);
    }
    public void deleteData(Student student){
        new DeleteTask().execute(student);
    }

    public LiveData<List<Student>> readData(){
        return read;
    }

    class  InsertTask extends AsyncTask<Student,Void,Void>{
        @Override
        protected Void doInBackground(Student... students) {
            studentDataBase.myDao().insert(students[0]);
            return null;
        }
    }

    class DeleteTask extends AsyncTask<Student,Void,Void>{
        @Override
        protected Void doInBackground(Student... students) {
            studentDataBase.myDao().delete(students[0]);
            return null;
        }
    }



}
