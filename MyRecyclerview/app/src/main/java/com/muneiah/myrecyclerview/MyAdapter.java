package com.muneiah.myrecyclerview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
Context ctx;
String titles[];
int posters[];
    /*alt+insert*/

    public MyAdapter(Context ctx, String[] titles, int[] posters) {
        this.ctx = ctx;
        this.titles = titles;
        this.posters = posters;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(ctx).inflate(R.layout.row_design,parent,false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.version_title.setText(titles[position]);
        holder.version_poster.setImageResource(posters[position]);
    }

    @Override
    public int getItemCount() {
        return posters.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView version_poster;
        TextView version_title;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            version_poster=itemView.findViewById(R.id.myimage);
            version_title=itemView.findViewById(R.id.tv_version_title);
        }
    }
}
