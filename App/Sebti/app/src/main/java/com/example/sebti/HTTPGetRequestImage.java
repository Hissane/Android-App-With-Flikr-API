package com.example.sebti;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HTTPGetRequestImage extends AsyncTask<String, Void, Bitmap> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 1500;
    public static final int CONNECTION_TIMEOUT = 1500;

    private ImageView imageview;

    public HTTPGetRequestImage(ImageView imageview) {
        this.imageview = imageview;
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap bmp = null;
        try {
            URL url = new URL(params[0]);
            bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bmp;
    }

    protected void onPostExecute(Bitmap bmp) {

        try {
            imageview.setImageBitmap(bmp);
        } catch (Exception e) {
            e.printStackTrace();
        }

        super.onPostExecute(bmp);
    }
}