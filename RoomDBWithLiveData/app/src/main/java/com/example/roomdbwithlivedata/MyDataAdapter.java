package com.example.roomdbwithlivedata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyDataAdapter extends RecyclerView.Adapter<MyDataAdapter.MyViewHolder> {

    Context ct;
    List<Student> list;
    public MyDataAdapter(MainActivity mainActivity, List<Student> studentList) {

        ct = mainActivity;
        list = studentList;

    }

    @NonNull
    @Override
    public MyDataAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(ct).inflate(R.layout.row_design,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDataAdapter.MyViewHolder holder, int position) {
        final Student student =  list.get(position);
        holder.readroll.setText(student.getRollNumber());
        holder.readname.setText(student.getName());
        holder.readmail.setText(student.getMailid());
        holder.readnumber.setText(student.getPhoneNumber());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //MainActivity.dataBase.myDao().delete(student);
                MainActivity.viewModel.delete(student);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView readname,readroll,readmail,readnumber;
        ImageView delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            readroll = itemView.findViewById(R.id.readRoll);
            readname = itemView.findViewById(R.id.readName);
            readmail = itemView.findViewById(R.id.readMail);
            readnumber = itemView.findViewById(R.id.readPhone);
            delete = itemView.findViewById(R.id.delete);

        }
    }
}
