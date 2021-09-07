package codes.codeoutprakhar.viewpager.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.adapters.SearchAdapter;
import codes.codeoutprakhar.viewpager.newsModel;

public class SearchFilterActivity extends AppCompatActivity {
    ImageView backP;
    SearchView searchView;
    RecyclerView recyclerView;
    SearchAdapter adapter;
    ArrayList<newsModel> list;
    RelativeLayout relativeLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_filter);
        backP = findViewById(R.id.backP);
        searchView = findViewById(R.id.serchViewA);
        recyclerView = findViewById(R.id.recyclerSearch);
        relativeLayout = findViewById(R.id.progress);
        list = new ArrayList<>();

        fetch();
        backP.setOnClickListener(v -> finish());
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });


    }

    void fetch() {
        String url = "https://inshortsv2.vercel.app/news?type=trending&limit=190";
        RequestQueue queue = Volley.newRequestQueue(SearchFilterActivity.this);
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, response -> {

                    relativeLayout.setVisibility(View.GONE);

                    try {
                        JSONArray arr = response.getJSONArray("articles");
                        for (int i = 0; i < arr.length(); i++) {
                            JSONObject obj = arr.getJSONObject(i);
                            newsModel newsModel = new newsModel();
                            newsModel.setTitle(obj.getString("title"));
                            newsModel.setDescription(obj.getString("description"));
                            newsModel.setImgUrl(obj.getString("image_url"));
                            newsModel.setSourceUrl(obj.getString("source_url"));
                            list.add(newsModel);


                        }

                    } catch (JSONException e) {
                        Toast.makeText(SearchFilterActivity.this, "something Went Wrong", Toast.LENGTH_SHORT).show();
                    }
                    adapter = new SearchAdapter(list, SearchFilterActivity.this);
                    recyclerView.setAdapter(adapter);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(SearchFilterActivity.this);
                    recyclerView.setLayoutManager(layoutManager);


                }, error -> Toast.makeText(SearchFilterActivity.this, "Error occurred", Toast.LENGTH_SHORT).show());
        queue.add(jsonObjectRequest);

    }
}