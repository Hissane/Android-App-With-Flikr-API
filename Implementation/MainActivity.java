package com.example.sebti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static com.example.sebti.HttpGetRequest.itemTitleStringList;

class MyImage {
    public String title;
    public String url;

    MyImage(String title) {
        this.title = title;
    }
}

public class MainActivity extends AppCompatActivity {
    ListView listView;
    List<MyImage> img_list = new ArrayList<MyImage>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        MyImage img1 = new MyImage("cat");
        MyImage img2 = new MyImage("dog");
        MyImage img3 = new MyImage("physics teacher");

        img_list.add(img1);
        img_list.add(img2);
        img_list.add(img3);

        //MyImage[] img_list = {img1, img2, img3};
        new HttpGetRequest().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, "https://www.flickr.com/services/feeds/photos_public.gne?tags=cats&format=json" );

        ImageAdapter adapter = new ImageAdapter(getApplicationContext(), (ArrayList<MyImage>) itemTitleStringList);
        listView = (ListView) findViewById(R.id.list_view);
        listView.setAdapter(adapter);
        //Log.v("JSON", itemTitleStringList.get(0).title);
    }

}
