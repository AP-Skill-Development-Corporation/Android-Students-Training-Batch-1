package com.example.activitylifecycletest;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.textview);
        tv.append("Oncreate \n");
        Log.d("MainActivity","Oncreate");
    }

    @Override
    protected void onStart() {
        super.onStart();
        tv.append("Onstart \n");
        Log.d("MainActivity","OnStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        tv.append("OnResume \n");
        Log.d("MainActivity","OnResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        tv.append("OnPause \n");
        Log.d("MainActivity","OnPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        tv.append("Onstop \n");
        Log.d("MainActivity","OnStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        tv.append("OnDestroy \n");
        Log.d("MainActivity","OnDestroy");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        tv.append("OnRestart \n");
        Log.d("MainActivity","OnRestart");
    }
}
