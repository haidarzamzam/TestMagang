package com.example.haidar.testmagang;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PostActivity extends AppCompatActivity implements PostAdapter.IPostAdapter {

    public static final String ITEM = "item";
    private static final String TAG = "PostActivty";
    String url = "http://jsonplaceholder.typicode.com/posts";
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<PostModel> postClassList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        recyclerView = findViewById(R.id.recyclerview_post);
        recyclerView.setAdapter(adapter);
        getDataPost();
    }

    public void getDataPost() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PostActivity.this, "Error Data", Toast.LENGTH_LONG);
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void getJSON(String request) {
        try {
            JSONArray jsonArray = new JSONArray(request);
            for (int x = 0; x < jsonArray.length(); x++) {
                JSONObject jObject = jsonArray.getJSONObject(x);
                PostModel post = new PostModel(
                        jObject.getInt("userId"),
                        jObject.getInt("id"),
                        jObject.getString("title"),
                        jObject.getString("body")
                );
                postClassList.add(post);
                adapter = new PostAdapter(postClassList, this);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    //recycler view click listener
    @Override
    public void doClick(int pos) {
        Intent i = new Intent(PostActivity.this, CommentActivity.class);
        i.putExtra(ITEM, postClassList.get(pos));
        startActivity(i);
    }
}
