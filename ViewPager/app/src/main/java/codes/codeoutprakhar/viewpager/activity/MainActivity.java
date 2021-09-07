package codes.codeoutprakhar.viewpager.activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import codes.codeoutprakhar.viewpager.fragments.AllFragment;
import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.fragments.SearchFragment;
import codes.codeoutprakhar.viewpager.fragments.TrendingFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        frameLayout = findViewById(R.id.frame);
        AllFragment all =  new AllFragment();
        TrendingFragment trend = new TrendingFragment();


        getSupportFragmentManager().beginTransaction().replace(R.id.frame, trend).commit();


        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()){


                    case R.id.trending:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,trend).commit();

                        break;
                    case R.id.all:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,all).commit();

                                break;

                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new SearchFragment()).commit();

                        break;



                }

                return true;
            }
        });
        bottomNavigationView.setOnNavigationItemReselectedListener(new BottomNavigationView.OnNavigationItemReselectedListener() {
            @Override
            public void onNavigationItemReselected(@NonNull MenuItem item) {
                switch (item.getItemId()){


                    case R.id.trending:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new TrendingFragment()).commit();

                        break;

                    case R.id.all:
                        getSupportFragmentManager().beginTransaction().replace(R.id.frame,new AllFragment()).commit();

                        break;


                }
            }
        });

    }
    @Override
    public void onBackPressed(){
        if (bottomNavigationView.getSelectedItemId()==R.id.trending){
            super.onBackPressed();
            finish();
        }
        else {
            bottomNavigationView.setSelectedItemId(R.id.trending);
        }
    }
}