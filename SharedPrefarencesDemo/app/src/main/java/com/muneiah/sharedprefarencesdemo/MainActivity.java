package com.muneiah.sharedprefarencesdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
EditText et_name,et_pass;
SharedPreferences sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name=findViewById(R.id.editText);
        et_pass=findViewById(R.id.editText2);
        sp=getSharedPreferences("apssdc",MODE_PRIVATE);
    }

    public void showData(View view) {
        String myname=et_name.getText().toString();
        String pass=et_pass.getText().toString();
        Toast.makeText(this, ""+myname+"\n"+pass, Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        String myname=et_name.getText().toString();
        String pass=et_pass.getText().toString();
        SharedPreferences.Editor editor=sp.edit();
        editor.putString("mykey",myname);
        editor.putString("mypasskey",pass);
        editor.apply();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String n=sp.getString("mykey","");
        String p=sp.getString("mypasskey","");
        et_name.setText(n);
        et_pass.setText(p);
    }
}
