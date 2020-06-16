package com.muneiah.myretrofitdemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MyPhotosAdapter extends RecyclerView.Adapter<MyPhotosAdapter.PhotosViewholder> {
  Context ctx;
  List<Response> responseList;

    public MyPhotosAdapter(Context ctx, List<Response> responseList) {
        this.ctx = ctx;
        this.responseList = responseList;
    }



    @NonNull
    @Override
    public PhotosViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(ctx).inflate(R.layout.row_design,parent,false);
        return new PhotosViewholder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotosViewholder holder, int position) {
    holder.photo_titile.setText(responseList.get(position).getTitle());
        Picasso.Builder builder=new Picasso.Builder(ctx);
        builder.downloader(new OkHttp3Downloader(ctx));
        builder.build().load(responseList.get(position).getThumbnailUrl())
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.photo);

    }

    @Override
    public int getItemCount() {
        return responseList.size();
    }

    public class PhotosViewholder extends RecyclerView.ViewHolder {
        ImageView photo;
        TextView photo_titile;
        public PhotosViewholder(@NonNull View itemView) {
            super(itemView);
            photo=itemView.findViewById(R.id.imageView);
            photo_titile=itemView.findViewById(R.id.textView);
        }
    }
}
