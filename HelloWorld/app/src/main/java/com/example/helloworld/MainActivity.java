package com.example.helloworld;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    //Thread life cycle methods
    //Activity is also one thread Main Thread or UI thread
    //Activity life cycle methods
  /*      Oncreate
        onstart
            onpause
    onstop
                    onResume,onRestart,Ondestroy*/
  //R.java file
  //If you want to access the res folder resources to the java file
  // we shoud use R.java
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
