package com.muneiah.myintents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
EditText myinput_url,et_ourMessge;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myinput_url=findViewById(R.id.inputUrl);
        et_ourMessge=findViewById(R.id.et_msg);
    }
    //With in the app screen we are navigating second screen
    //explicit intent
    public void navigate_secondScreen(View view) {
        String mymessge_input=et_ourMessge.getText().toString();
        Intent myintentObj=new Intent(this,SecondActivity.class);
        //By using this intentobj.putExtra() it will be sore the data as key and value pair
        myintentObj.putExtra("apssdc",mymessge_input);
        startActivity(myintentObj);
    }
//Implicit Intent
    public void openBroswer(View view) {
        //from getting input from user
        String url=myinput_url.getText().toString();

        Uri uri=Uri.parse("https://www."+url+".com");
        Intent obj=new Intent(Intent.ACTION_VIEW,uri);
        startActivity(obj);

    }
}
