package com.example.haidar.testmagang;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
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

public class CommentActivity extends AppCompatActivity {

    String url;
    RecyclerView recyclerView;
    RecyclerView.Adapter adapter;
    List<CommentModel> commentClassList = new ArrayList<>();
    PostModel pModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comment);

        recyclerView = findViewById(R.id.recyclerview_comment);
        recyclerView.setAdapter(adapter);
        pModel = (PostModel) getIntent().getSerializableExtra(PostActivity.ITEM);
        getDataPost();

        //url = "http://jsonplaceholder.typicode.com/posts/" + pModel.getId() + "/comments";
        Log.d("comment", "onCreate: " + url);
    }

    public void getDataPost() {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, "http://jsonplaceholder.typicode.com/posts/" + pModel.getId() + "/comments", new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                getJSON(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CommentActivity.this, "Error Data", Toast.LENGTH_LONG);
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
                CommentModel comment = new CommentModel(
                        jObject.getInt("postId"),
                        jObject.getInt("id"),
                        jObject.getString("name"),
                        jObject.getString("email"),
                        jObject.getString("body")
                );
                commentClassList.add(comment);
                adapter = new CommentAdapter(commentClassList);
                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
                recyclerView.setLayoutManager(linearLayoutManager);
                recyclerView.setAdapter(adapter);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }
}
