package com.example.sebti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.net.URL;
import java.util.ArrayList;

public class ImageAdapter extends ArrayAdapter<MyImage> {
    public ImageAdapter(Context context, ArrayList<MyImage> images) {
        super(context, 0, images);
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        MyImage image = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.single_item_layout, parent, false);
        }
        // Lookup view for data population
        ImageView image_item = (ImageView) convertView.findViewById(R.id.single_item_tv);
        // Populate the data into the template view using the data object
        new HTTPGetRequestImage(image_item).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, image.url);
        // Return the completed view to render on screen
        return convertView;
    }
}