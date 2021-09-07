package codes.codeoutprakhar.viewpager.fragments;


import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import codes.codeoutprakhar.viewpager.adapters.NewsAdapter;
import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.newsModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class TrendingFragment extends Fragment {

    ViewPager2 recyclerView;
    ImageView gifView;
    RelativeLayout progressLayout;
    SwipeRefreshLayout swipeRefreshLayout;
    ArrayList<newsModel> list;
    NewsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_trending, container, false);

        progressLayout = view.findViewById(R.id.progress);
        recyclerView = view.findViewById(R.id.recycle);
        swipeRefreshLayout = view.findViewById(R.id.refresh);
        list = new ArrayList<>();

        fetch();
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                list.clear();
                fetch();
                adapter.notifyDataSetChanged();
                Toast.makeText(getContext(),"Updated",Toast.LENGTH_SHORT).show();
                swipeRefreshLayout.setRefreshing(false);
            }
        });




        return view;
    }

    void fetch(){
        String url = "https://inshortsv2.vercel.app/news?type=trending&limit=100";
        RequestQueue queue = Volley.newRequestQueue(getContext());
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
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
                            adapter = new NewsAdapter(list,getContext());
                            recyclerView.setAdapter(adapter);
                        } catch (JSONException e) {
                            Toast.makeText(getContext(),"connection time out",Toast.LENGTH_SHORT).show();
                        }


                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getContext(),"Network Error ",Toast.LENGTH_SHORT).show();

                    }
                });
        queue.add(jsonObjectRequest);

    }

}