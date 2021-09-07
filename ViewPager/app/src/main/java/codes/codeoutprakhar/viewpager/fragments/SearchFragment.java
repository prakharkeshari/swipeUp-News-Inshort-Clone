package codes.codeoutprakhar.viewpager.fragments;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.activity.SearchFilterActivity;
import codes.codeoutprakhar.viewpager.activity.TopicsActivity;

public class SearchFragment extends Fragment {
    ImageView India,Business,Politics,Sports,Technology,Hatke,Startup,Entertainment,International,Automobile,Science,Travel,Miscellanous,Fashion,Education;
    LottieAnimationView searchAnimation;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search, container, false);
        India = view.findViewById(R.id.india);
        Business = view.findViewById(R.id.business);
        Politics = view.findViewById(R.id.politics);
        Sports = view.findViewById(R.id.sports);
        Technology = view.findViewById(R.id.tech);
        Hatke = view.findViewById(R.id.hatke);
        Startup = view.findViewById(R.id.startup);
        Entertainment = view.findViewById(R.id.entertain);
        International = view.findViewById(R.id.international);
        Automobile = view.findViewById(R.id.automobile);
        Science = view.findViewById(R.id.science);
        Travel = view.findViewById(R.id.travel);
        Miscellanous = view.findViewById(R.id.miscell);
        Fashion = view.findViewById(R.id.fashion);
        Education = view.findViewById(R.id.education);
        searchAnimation = view.findViewById(R.id.animationView);

        searchAnimation.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), SearchFilterActivity.class);
            startActivity(intent);
        });

        India.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), TopicsActivity.class);
            String send = "national";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Business.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "business";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Politics.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "politics";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Sports.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "sports";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Technology.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "technology";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Hatke.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "hatke";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Startup.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "startups";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Entertainment.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "entertainment";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        International.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "world";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Automobile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "automobile";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Science.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "science";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Travel.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "travel";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Miscellanous.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "miscellaneous";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Fashion.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "fashion";
            intent.putExtra("key",send);
            startActivity(intent);
        });
        Education.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(),TopicsActivity.class);
            String send = "education";
            intent.putExtra("key",send);
            startActivity(intent);
        });

        return view;
    }

}