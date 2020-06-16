package com.muneiah.myretrofitdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;


public class MainActivity extends AppCompatActivity {
RecyclerView rv;
ProgressDialog progrss;
MyPhotosAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.myrecyerview);
        progrss=new ProgressDialog(this);
        EndPointDataInterface service=RetrofitInstance.getJsonResponds().create(EndPointDataInterface.class);
        Call<List<Response>> call=service.getAllPhotos();
        call.enqueue(new Callback<List<Response>>() {
            @Override
            public void onResponse(Call<List<Response>> call, retrofit2.Response<List<Response>> response) {
                progrss.dismiss();
                getDataService(response.body());
            }

            @Override
            public void onFailure(Call<List<Response>> call, Throwable t) {

            }
        });
    }

    private void getDataService(List<Response> body) {
        adapter=new MyPhotosAdapter(this,body);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);
    }


}
