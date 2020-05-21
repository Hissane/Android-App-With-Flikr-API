package com.example.sebti;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

class HttpGetRequest extends AsyncTask<String, Void, String> {
    public static final String REQUEST_METHOD = "GET";
    public static final int READ_TIMEOUT = 1500;
    public static final int CONNECTION_TIMEOUT = 1500;
    public static List<MyImage> itemTitleStringList = new ArrayList<MyImage>();

    @Override
    protected String doInBackground(String... params){
        String stringUrl = params[0];
        String result;
        String inputLine;
        try {
            //Create a URL object holding our url
            URL myUrl = new URL(stringUrl);
            //Create a connection
            HttpURLConnection connection =(HttpURLConnection)
                    myUrl.openConnection();
            //Set methods and timeouts
            connection.setRequestMethod(REQUEST_METHOD);
            connection.setReadTimeout(READ_TIMEOUT);
            connection.setConnectTimeout(CONNECTION_TIMEOUT);

            //Connect to our url
            connection.connect();
            //Create a new InputStreamReader
            InputStreamReader streamReader = new
                    InputStreamReader(connection.getInputStream());
            //Create a new buffered reader and String Builder
            BufferedReader reader = new BufferedReader(streamReader);
            StringBuilder stringBuilder = new StringBuilder();
            //Check if the line we are reading is not null
            while((inputLine = reader.readLine()) != null){
                stringBuilder.append(inputLine);
            }
            //Close our InputStream and Buffered reader
            reader.close();
            streamReader.close();
            //Set our result equal to our stringBuilder
            result = stringBuilder.toString();
        }
        catch(IOException e){
            e.printStackTrace();
            result = null;
        }
        return result;
    }
    protected void onPostExecute(String result){
        JSONObject jsonObject;
        JSONArray itemJSONArray;

        String jsonString = result.substring(15, result.length() -1);
        try {
            jsonObject = new JSONObject(jsonString);
            itemJSONArray = jsonObject.getJSONArray("items");
            for(int i = 0; i < itemJSONArray.length(); i++){
                MyImage var = new MyImage(itemJSONArray.getJSONObject(i).getString("title"));
                itemTitleStringList.add(var);
                System.out.println(var.title);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        //Log.v("JSON", itemTitleStringList.get(0).title);
        //STOPPED ON EXERCISE 10 (not done yet)
        super.onPostExecute(result);
    }
}