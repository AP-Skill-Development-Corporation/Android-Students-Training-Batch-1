package com.example.pickersanddialogs;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import java.util.Calendar;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    //InterFace will consist unimplemented methods

    Button b_alertdialog,b_datepicker,b_timepicker;
    //If you want to handle the onlclick event for these buttons
    // in java we have Onclicklistner classes

    int c_year,c_month,c_date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        b_alertdialog = findViewById(R.id.alertdilog_button);
        b_datepicker = findViewById(R.id.datepicker_button);
        b_timepicker = findViewById(R.id.timePicker_button);

        b_alertdialog.setOnClickListener(this);
        b_datepicker.setOnClickListener(this);
        b_timepicker.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.alertdilog_button:
                openAlertDialog();
                break;
            case R.id.datepicker_button:
                openDatePicker();
                break;
            case R.id.timePicker_button:
                openTimePicker();
                break;

        }

    }

    private void openTimePicker() {

    }

    private void openDatePicker() {
        //If you want work with datepicker dialog there is a one predefined class called
            // DatePickerDialog
        Calendar c = Calendar.getInstance();
        c_year = c.get(Calendar.YEAR);
        c_month = c.get(Calendar.MONTH);
        c_date = c.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String myDate = dayOfMonth+"-"+(month+1)+"-"+year;
                Toast.makeText(MainActivity.this,
                        myDate, Toast.LENGTH_SHORT).show();

            }
        },c_year,c_month,c_date);
        datePickerDialog.show();




    }

    private void openAlertDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setIcon(R.mipmap.ic_launcher);
        builder.setTitle("Exit");
        builder.setMessage("Are you sure want Exit ?");
        builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        builder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.show();







    }
}
