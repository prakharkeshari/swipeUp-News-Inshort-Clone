package codes.codeoutprakhar.viewpager.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;

import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.adapters.NewsAdapter;
import codes.codeoutprakhar.viewpager.newsModel;

import java.util.ArrayList;

public class DetailSearchActivity extends AppCompatActivity {
   ViewPager2 viewPager2;
   NewsAdapter newsAdapter;
   ArrayList<newsModel> list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_search);
        viewPager2 = findViewById(R.id.viewPagers);
        Bundle bundle = getIntent().getExtras();
        list =(ArrayList<newsModel>) bundle.getSerializable("prakhar");

        Intent intent = getIntent();
        int positions = intent.getIntExtra("pos",0);


        newsAdapter = new NewsAdapter(list,DetailSearchActivity.this);
        viewPager2.setAdapter(newsAdapter);
        viewPager2.setCurrentItem(positions);



    }
}