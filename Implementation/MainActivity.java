package com.example.sebti;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

class MyImage {
    public String title;
    public String url;

    MyImage(String title, String url) {
        this.title = title;
        this.url = url;
    }
}

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<MyImage> img_list = new ArrayList<MyImage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.list_view);
        new HTTPGetRequest(getApplicationContext(), listView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://www.flickr.com/services/feeds/photos_public.gne?format=json&tags=cats" );

    }


}
