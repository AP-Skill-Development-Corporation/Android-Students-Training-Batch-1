package com.example.hellotoast;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    int count = 0;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv=findViewById(R.id.dataTextview);
    }

    public void displayToast(View view) {
        Toast.makeText(this,"Welcome to Android Workshop",Toast.LENGTH_LONG).show();

    }

    public void increaseCount(View view) {
            count++;
           tv.setText(""+count);
    }
}
