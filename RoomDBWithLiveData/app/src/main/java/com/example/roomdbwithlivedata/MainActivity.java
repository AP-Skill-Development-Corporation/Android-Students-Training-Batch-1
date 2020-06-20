package com.example.roomdbwithlivedata;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //static StudentDataBase dataBase;

    RecyclerView rv;

    List<Student> studentList;

    static StudentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv =findViewById(R.id.recycler);

        //studentList = dataBase.myDao().readData();

        /*dataBase = Room.databaseBuilder(context,
                    StudentDataBase.class,"MyDb")
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration().build();*/

        viewModel = new ViewModelProvider(this).get(StudentViewModel.class);

        viewModel.readData().observe(this, new Observer<List<Student>>() {
            @Override
            public void onChanged(List<Student> students) {
                rv.setLayoutManager(new LinearLayoutManager(MainActivity.this));
                rv.setAdapter(new MyDataAdapter(MainActivity.this,students));
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.add:
                ViewGroup viewGroup=findViewById(android.R.id.content);
                View v = LayoutInflater.from(MainActivity.this)
                        .inflate(R.layout.insertdata, viewGroup,false);
                final EditText name = v.findViewById(R.id.addname);
                final EditText mail = v.findViewById(R.id.addmail);
                final EditText phone = v.findViewById(R.id.addphone);
                final EditText rollnum = v.findViewById(R.id.addrollnum);
                Button save = v.findViewById(R.id.save);

                final BottomSheetDialog dialog = new BottomSheetDialog(MainActivity.this);
                dialog.setContentView(v);
                save.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String add_name = name.getText().toString();
                        String add_mail = mail.getText().toString();
                        String add_phone = phone.getText().toString();
                        String add_roll = rollnum.getText().toString();

                        Student student = new Student();
                        student.setName(add_name);
                        student.setMailid(add_mail);
                        student.setPhoneNumber(add_phone);
                        student.setRollNumber(add_roll);

                        //dataBase.myDao().insert(student);

                        viewModel.insert(student);

                        dialog.dismiss();
                    }
                });
                dialog.show();

                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
