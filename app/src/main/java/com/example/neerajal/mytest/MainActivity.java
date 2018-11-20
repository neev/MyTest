package com.example.neerajal.mytest;

import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.GridView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        URL catUrl = null;
        try {
            catUrl = new URL("https://api.thecatapi.com/v1/images/search?size=full&mime_types=jpg,png,gif&format=json&order=RANDOM&page=0&limit=10&category_ids");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        new ImageAsyncTask().execute(catUrl);




            GridView gridview = (GridView) findViewById(R.id.gridview);
            gridview.setAdapter(new ImageAdapter(this));

            gridview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View v,
                                        int position, long id) {
                    // Toast.makeText(HelloGridView.this, "" + position,
                    // Toast.LENGTH_SHORT).show();
                }
            });

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);


        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    ///Async task class

    private class ImageAsyncTask extends AsyncTask<URL, Integer, List<Bitmap>> {

        private String resp;
        ProgressDialog progressDialog;
        String jsonResult;
        String inputLine;

        protected void onProgressUpdate(Integer... progress) {
            //setProgressPercent(progress[0]);
        }

        @Override
        protected List<Bitmap> doInBackground(URL... urls) {

            URL url = null;
            Bitmap bmp = null;
            List<String> imageurl_list = new ArrayList<>();
            List<Bitmap> images= new ArrayList<>();
            InputStream in = null;


            try {
                url = new URL("https://api.thecatapi.com/v1/images/search?size=full&mime_types=jpg,png,gif&format=json&order=RANDOM&page=0&limit=10&category_ids");
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            HttpURLConnection urlConnection = null;
            try {
                urlConnection = (HttpURLConnection) url.openConnection();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //InputStream streamReader = new BufferedInputStream(urlConnection.getInputStream());

                //Create a new InputStreamReader
                InputStreamReader streamReader = new
                        InputStreamReader(urlConnection.getInputStream());
                List<CatAPIJson> jsonResult = new ArrayList<>();
                //Create a new buffered reader and String Builder
                BufferedReader reader = new BufferedReader(streamReader);
                StringBuilder stringBuilder = new StringBuilder();
                //Check if the line we are reading is not null
                while ((inputLine = reader.readLine()) != null) {
                    stringBuilder.append(inputLine);

                }

                //Close our InputStream and Buffered reader
                reader.close();
                streamReader.close();
                //Set our result equal to our stringBuilder
                //jsonResult = stringBuilder.toString();


                String response = stringBuilder.toString();
                JSONArray jsonArray = new JSONArray(response);


                //JSONArray jsonArray = jsonObject.getJSONArray("breeds");
                for(int i=0; i<jsonArray.length();i++)
                {
                    //jsonResult.add(jsonArray.getJSONArray(i));
                    JSONObject jsonObj = jsonArray.getJSONObject(i);
                    String id = jsonObj.getString("id");
                    String imageUrl = jsonObj.getString("url");
                    imageurl_list.add(imageUrl);

                }

                //System.out.println(imageurl_list);


                    for(int i=0; i<imageurl_list.size();i++) {
                        in = new java.net.URL(imageurl_list.get(i)).openStream();
                        bmp = BitmapFactory.decodeStream(in);
                        images.add(bmp);
                    }

                in.close();

            }catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();

            }

            return images;
        }

        protected void onPostExecute(List<Bitmap> images) {
            //showDialog("Downloaded " + result + " bytes");
            //super.onPostExecute();
            for(int i=0;i<images.size();i++) {
                ImageAdapter.imagesfromCatAPI.add(images.get(i));
            }
            System.out.println("Images from cat api : "+images);
            System.out.println("from Asynctask"+ImageAdapter.imagesfromCatAPI.size());

        }
    }



}
