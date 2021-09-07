package codes.codeoutprakhar.viewpager.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.adapters.NewsAdapter;
import codes.codeoutprakhar.viewpager.newsModel;

public class TopicsActivity extends AppCompatActivity {
    ViewPager2 recyclerView;
    ImageView Back;
    RelativeLayout progressLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<newsModel> list;
    NewsAdapter adapter;
    String urlType;
    TextView nameOfCategory;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        nameOfCategory = findViewById(R.id.nameOfCategory);
        Back = findViewById(R.id.backToSearch);
        progressLayout =findViewById(R.id.progress);
        recyclerView = findViewById(R.id.recycle);
        list = new ArrayList<>();
        Intent intent = getIntent();
        urlType =  intent.getStringExtra("key");
        nameOfCategory.setText(urlType);


        swipeRefreshLayout =findViewById(R.id.refresh);
        swipeRefreshLayout.setOnRefreshListener(() -> {
            list.clear();
            fetch();
            adapter.notifyDataSetChanged();
            Toast.makeText(TopicsActivity.this,"Updated",Toast.LENGTH_SHORT).show();
            swipeRefreshLayout.setRefreshing(false);
        });

        fetch();
        Back.setOnClickListener(v -> finish());
    }
    void fetch(){
        String url = "https://inshortsv2.vercel.app/news?type="+urlType+"&limit=100";
        RequestQueue queue = Volley.newRequestQueue(TopicsActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {
                    progressLayout.setVisibility(View.GONE);

                    try {
                        JSONArray arr  = response.getJSONArray("articles");
                        for(int i =0;i<arr.length();i++){
                            JSONObject obj = arr.getJSONObject(i);
                            newsModel newsModel = new newsModel();
                            newsModel.setTitle(obj.getString("title"));
                            newsModel.setDescription(obj.getString("description"));
                            newsModel.setImgUrl(obj.getString("image_url"));
                            newsModel.setSourceUrl(obj.getString("source_url"));
                            list.add(newsModel);


                        }

                    } catch (JSONException e) {
                        Toast.makeText(TopicsActivity.this,"something Went Wrong",Toast.LENGTH_SHORT).show();
                    }
                    adapter = new NewsAdapter(list,TopicsActivity.this);
                    recyclerView.setAdapter(adapter);

                }, error -> Toast.makeText(TopicsActivity.this,"Error occurred",Toast.LENGTH_SHORT).show());
        queue.add(jsonObjectRequest);

    }
}