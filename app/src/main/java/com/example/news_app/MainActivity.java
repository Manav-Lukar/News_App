package com.example.news_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.news_app.adapter.NewsAdapter;
import com.example.news_app.databinding.ActivityMainBinding;
import com.example.news_app.model.NewsData;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    List<NewsData> newsDataList;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        newsDataList = new ArrayList<>();
        newsAdapter = new NewsAdapter(newsDataList);
        binding.recyclerNews.setAdapter(newsAdapter);
        getData();

    }


    private void getData(){
        RequestQueue queue = Volley.newRequestQueue(getApplicationContext());
        String url = "https://newsapi.org/v2/top-headlines?country=id&apiKey=e8f8cb4b8d3a404a81287bf0a7c2d435";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    String status = response.getString("status");
                    if(status.equals("ok")){
                        JSONArray jsonArray = response.getJSONArray("articles");
                        for(int i = 0;i<jsonArray.length();i++){
                            JSONObject news = jsonArray.getJSONObject(i);
                            String title = news.getString("title");
                            String content = news.getString("description");
                            String url = news.getString("url");
                            String imgUrl = news.getString("urlToImage");
                            Log.d("Amritanshu", "Title :" + title);
                            NewsData nd = new NewsData(title, content, imgUrl, url);

                            newsDataList.add(nd);
                        }
                        newsAdapter.notifyDataSetChanged();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> header = new HashMap<>();
                header.put("User-Agent", "PostmanRuntime/7.29.0");
                return header;
            }
        };
        queue.add(jsonObjectRequest);
     }
    }

