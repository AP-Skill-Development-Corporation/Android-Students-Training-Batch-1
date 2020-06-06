package com.muneiah.myintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        tv=findViewById(R.id.tv_mymessage_data);
        Intent myintent=getIntent();
        String data=myintent.getStringExtra("apssdc");
        tv.setText(data);
    }
}

/*task*/
/*create a new project .in that project take one edittext and button .suppose we enter the mobile number as input open th dail pad and show that mobile number on dail pad  */
/*Refer the google slides 2.1*/