package com.example.menustest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mymenu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.aboutus:
                Toast.makeText(this, "This is Aboutus",
                        Toast.LENGTH_SHORT).show();
                break;
            case R.id.contactus:
                Toast.makeText(this,
                        "This is ContactUs", Toast.LENGTH_SHORT).show();
            break;
            case R.id.settings:
                Toast.makeText(this,
                        "This is Settings", Toast.LENGTH_SHORT).show();
                break;
        }

        return true;
    }
}
