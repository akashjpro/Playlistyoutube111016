package com.example.akashjpro.playlistyoutube111016;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String API_KEY =  "AIzaSyCax_paqrKloMoL2aYAZTcnpCnhdCWr5_E";
    String PLAYLIST_ID = "PLC7VoaA2DTCLgAH396ER9Oc2POXTAjdAn";
    String LINK = "https://www.googleapis.com/youtube/v3/playlistItems?part=snippet&playlistId="+ PLAYLIST_ID +"&key="+ API_KEY +"&maxResults=50";

    ListView lvListVideo;
    ArrayList<Video> mangVideo;
    VideoAdapter videoAdapter;
    ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();

        mangVideo = new ArrayList<Video>();
        videoAdapter = new VideoAdapter(MainActivity.this, R.layout.item_video, mangVideo);
        lvListVideo.setAdapter(videoAdapter);

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                new GET_JSON_YOUTUBE().execute(LINK);
            }
        });

        lvListVideo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this, PlayNew.class);
                intent.putExtra("IDVIDEO", mangVideo.get(position).getVideoID());
                startActivity(intent);
            }
        });

    }

    private void addControls() {
        lvListVideo = (ListView) findViewById(R.id.lvPlayList);
    }

    private  class GET_JSON_YOUTUBE extends AsyncTask<String, String, String>{

        @Override
        protected String doInBackground(String... params) {
            return docNoiDung_Tu_URL(params[0]);
        }

        @Override
        protected void onPostExecute(String s) {
            //Toast.makeText(MainActivity.this, s, Toast.LENGTH_SHORT).show();
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray jsonItems = object.getJSONArray("items");
                String url          = "";
                String title        = "";
                String videoId      = "";
                String channelTitle = "";
                Toast.makeText(MainActivity.this, "size: "+jsonItems.length() , Toast.LENGTH_SHORT).show();
                for (int i=0; i<23; i++){
                    Toast.makeText(MainActivity.this, "i= "+i, Toast.LENGTH_SHORT).show();
                    JSONObject objectItem = jsonItems.getJSONObject(i);
                    JSONObject jsonSnippet = objectItem.getJSONObject("snippet");
                    //Get title
                    title = jsonSnippet.getString("title");
                    //Get url
                    JSONObject objectThumbnails = jsonSnippet.getJSONObject("thumbnails");
                    JSONObject objectDefault = null;
                    Toast.makeText(MainActivity.this, "Size: "+ objectThumbnails.length(), Toast.LENGTH_SHORT ).show();
                    int size = objectThumbnails.length();
                    switch (size){
                        case 1:
                            objectDefault = objectThumbnails.getJSONObject("default");
                            break;
                        case 2:
                            objectDefault = objectThumbnails.getJSONObject("medium");
                            break;

                        case 3:
                            objectDefault = objectThumbnails.getJSONObject("high");
                            break;
                        case 4:
                            objectDefault = objectThumbnails.getJSONObject("standard");
                            break;
                        default:
                            objectDefault = objectThumbnails.getJSONObject("maxres");
                            break;

                    }
                    url = objectDefault.getString("url");
                    //Get videoID
                    JSONObject objectResourceId = jsonSnippet.getJSONObject("resourceId");
                    videoId = objectResourceId.getString("videoId");

                    //Get channelTitle
                    channelTitle = jsonSnippet.getString("channelTitle");
                    mangVideo.add(new Video(videoId, title, channelTitle, url));
                    videoAdapter.notifyDataSetChanged();
                }




            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    private static String docNoiDung_Tu_URL(String theUrl)
    {
        StringBuilder content = new StringBuilder();

        try
        {
            // create a url object
            URL url = new URL(theUrl);

            // create a urlconnection object
            URLConnection urlConnection = url.openConnection();

            // wrap the urlconnection in a bufferedreader
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

            String line;

            // read from the urlconnection via the bufferedreader
            while ((line = bufferedReader.readLine()) != null)
            {
                content.append(line + "\n");
            }
            bufferedReader.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return content.toString();
    }


}
