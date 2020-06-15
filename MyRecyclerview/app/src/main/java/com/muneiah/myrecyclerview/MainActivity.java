package com.muneiah.myrecyclerview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
MyAdapter adapter;
String androidTitles[];
int logos[];
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.myRecyclerview);
        androidTitles=getResources().getStringArray(R.array.version_names);
        logos=new int[]{R.drawable.cupcake,
                        R.drawable.donut,
                        R.drawable.eclair,
                        R.drawable.froyo,
        R.drawable.gingerbread,
        R.drawable.honeycomb,
        R.drawable.ice,
        R.drawable.jelly_bean,
        R.drawable.kitkat,
        R.drawable.lollipop,
        R.drawable.marsmellow,
        R.drawable.nougat,
        R.drawable.oreo,
        R.drawable.pie,
        R.drawable.que,
        R.drawable.rr};
        adapter=new MyAdapter(this,androidTitles,logos);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }
}
