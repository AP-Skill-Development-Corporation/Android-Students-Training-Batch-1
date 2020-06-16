package com.muneiah.myretrofitdemo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EndPointDataInterface
{
    @GET("/photos")
    Call<List<Response>> getAllPhotos();
}
