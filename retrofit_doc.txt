# Retrofit— A simple implimentation in Android 

* For this document, we are going to see how to use Retrofit HTTP client in your Android application.
* [Official link for Retrofit](https://square.github.io/retrofit/)

**Retrofit is an awesome type-safe HTTP client for Android and Java built by awesome folks at Square. Retrofit makes it easy to consume JSON or XML data which is parsed into Plain Old Java Objects (POJOs).**
####  So, without any further delays, lets get started by first creating a new project in Android Studio.
1. Go to File ⇒ New Project. When it prompts you to select the default activity, select Empty Activity and proceed.
2. Open **build.gradle in (Module:app)** and add **Retrofit, Picasso, RecyclerView, Gson dependencies** like this.
    * After adding below dependencies click on top right side corner **Sync Now** .Wait untill for successfull message came
``` xml
//If app crash then only use below compileOption 
android 
{
  compileOptions 
	{
        sourceCompatibility JavaVersion.VERSION_1_8
        targetCompatibility JavaVersion.VERSION_1_8
  	}
}

dependencies 
{
    implementation 'com.squareup.retrofit2:retrofit:2.8.1'
    implementation 'com.squareup.retrofit2:converter-gson:2.7.2'
    implementation 'com.jakewharton.picasso:picasso2-okhttp3-downloader:1.1.0'
    implementation 'com.squareup.picasso:picasso:2.71828'
    implementation 'androidx.recyclerview:recyclerview:1.2.0-alpha03'
    implementation 'androidx.cardview:cardview:1.0.0'
}


```

3. Don’t forget to add INTERNET permissions in AndroidManifest.xml file like this

```Xml

<?xml version="1.0" encoding="utf-8"?>
<manifest xmlns:android="http://schemas.android.com/apk/res/android"
    package="com.muneiah.retrofitpractices1">
    
    <!--For Internet access -->
<uses-permission android:name="android.permission.INTERNET"/>
    
    <application
        android:allowBackup="true"
        android:icon="@mipmap/ic_launcher"
        android:label="@string/app_name"
        android:roundIcon="@mipmap/ic_launcher_round"
        android:supportsRtl="true"
        android:theme="@style/AppTheme">
        <activity android:name=".MainActivity">
            <intent-filter>
                <action android:name="android.intent.action.MAIN" />

                <category android:name="android.intent.category.LAUNCHER" />
            </intent-filter>
        </activity>
    </application>

</manifest>


```

4. Next, we will create data model to parse our sample JSON data with following structure.[For Json Url](https://jsonplaceholder.typicode.com/photos)
<img src="https://github.com/Muneiahtellakula/kotlin-learningTasks/blob/master/jsonplaceholder.png">

 * Create a class named Repo.java under model package like this

```Java
package com.muneiah.retrofitpractices1;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

public class Repo implements Serializable {

	@SerializedName("albumId")
	private int albumId;

	@SerializedName("id")
	private int id;

	@SerializedName("title")
	private String title;

	@SerializedName("url")
	private String url;

	@SerializedName("thumbnailUrl")
	private String thumbnailUrl;

	public void setAlbumId(int albumId){
		this.albumId = albumId;
	}

	public int getAlbumId(){
		return albumId;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setTitle(String title){
		this.title = title;
	}

	public String getTitle(){
		return title;
	}

	public void setUrl(String url){
		this.url = url;
	}

	public String getUrl(){
		return url;
	}

	public void setThumbnailUrl(String thumbnailUrl){
		this.thumbnailUrl = thumbnailUrl;
	}

	public String getThumbnailUrl(){
		return thumbnailUrl;
	}

	@Override
 	public String toString(){
		return 
			"Repo{" + 
			"albumId = '" + albumId + '\'' + 
			",id = '" + id + '\'' + 
			",title = '" + title + '\'' + 
			",url = '" + url + '\'' + 
			",thumbnailUrl = '" + thumbnailUrl + '\'' + 
			"}";
		}
}

```

5. Create the Retrofit Instance
* To issue network requests to a REST API with Retrofit, we need to create an instance using the Retrofit.Builder class and configure it with a base URL.
* Create a class **RetrofitClientInstance.java** under **network** package. Here **BASE_URL** is the basic URL of our API where we will make a call.

```Java
package com.muneiah.retrofitpractices1;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitClientInstance 
{
    private static Retrofit retrofit;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com";

    public static Retrofit getRetrofitInstance() {
        if (retrofit == null) {
            retrofit = new retrofit2.Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}

```
6. Define the Endpoints:Create New Interface name GetDataService

```
package com.muneiah.retrofitpractices1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService
{
    @GET("/photos")
    Call<List<Repo>> getAllPhotos();
}

7.Create layour resourse each_item_design.xml file like as 

```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    android:weightSum="2"
    android:layout_weight="10"
    android:layout_margin="10dp"
    android:layout_marginTop="10dp"
    android:orientation="horizontal" android:layout_width="match_parent"
    android:layout_height="wrap_content">

    <ImageView
        android:id="@+id/iv"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        app:srcCompat="@drawable/ic_launcher_background" />

    <TextView
        android:id="@+id/textView_tv"
        android:layout_width="0dp"
        android:layout_weight="1"
        android:layout_height="wrap_content"
        android:text="TextView" />
</LinearLayout>


```



```
8. Create custom adapter for binding data with RecycleView.
* Create a class named **MyAdapter.java** under java directory first package like this.
```
package com.muneiah.retrofitpractices1;

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

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
     List<Repo> dataList;
     Context context;
     //constractor
    public MyAdapter(List<Repo> dataList, Context context) {
        this.dataList = dataList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.each_item_design,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.tvv_tit.setText(dataList.get(position).getTitle());
        Picasso.Builder builder = new Picasso.Builder(context);
        builder.downloader(new OkHttp3Downloader(context));
        builder.build().load(dataList.get(position).getThumbnailUrl())
                .placeholder((R.drawable.ic_launcher_background))
                .error(R.drawable.ic_launcher_background)
                .into(holder.iv_photho);

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_photho;
        TextView tvv_tit;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_photho=itemView.findViewById(R.id.iv);
            tvv_tit=itemView.findViewById(R.id.textView_tv);
        }
    }
}


```


8.Add the Recylerview Component in the activity_main.xml file just like as


```
<?xml version="1.0" encoding="utf-8"?>
<LinearLayout
    android:orientation="vertical"
    xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context=".MainActivity">

    <androidx.recyclerview.widget.RecyclerView
        android:layout_width="match_parent"
        android:layout_height="wrap_content"
        android:id="@+id/rec" />

</LinearLayout>

```

9. Final step
* Inside the onCreate() method of the MainActivity.java, we initialize an instance of the GetDataService interface, the RecyclerView, and also the adapter. Finally, we call the generateDataList() method.

```
package com.muneiah.retrofitpractices1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
RecyclerView rv;
MyAdapter adapter;
ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv=findViewById(R.id.rec);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setMessage("Loading....");
        progressDialog.show();
        /*Create handle for the RetrofitInstance interface*/
        GetDataService service = RetrofitClientInstance.getRetrofitInstance().create(GetDataService.class);

        Call<List<Repo>> call = service.getAllPhotos();
        call.enqueue(new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                progressDialog.dismiss();
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(MainActivity.this, "Something went wrong...Please try later!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    /*Method to generate List of data using RecyclerView with custom adapter*/
    private void generateDataList(List<Repo> photoList) {
        adapter = new MyAdapter(this,photoList);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);
        rv.setAdapter(adapter);
    }
}


```



* Understanding enqueue()
enqueue() asynchronously sends the request and notifies your app with a callback when a response comes back. Since this request is asynchronous, Retrofit handles it on a background thread so that the main UI thread isn't blocked or interfered with.

	To use enqueue(), you have to implement two callback methods:
	* onResponse()
	* onFailure()
	
	
* **Run the Application**

## Output Screen 

<img src="https://github.com/Muneiahtellakula/kotlin-learningTasks/blob/master/20200531_130811-ANIMATION.gif">


