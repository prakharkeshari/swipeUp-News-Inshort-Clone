package codes.codeoutprakhar.viewpager.adapters;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.activity.DetailSearchActivity;
import codes.codeoutprakhar.viewpager.newsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class SearchAdapter extends RecyclerView.Adapter<SearchAdapter.SearchViewHolder> implements Filterable {

    ArrayList<newsModel> list;
    ArrayList<newsModel> backup;
    Context context;

    public SearchAdapter(ArrayList<newsModel> list, Context context) {
        this.list = list;
        this.context = context;
        backup = new ArrayList<>(list);
    }


    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.search_sample,parent,false);
        return new SearchViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
        newsModel news =  list.get(position);
        holder.textViewSearch.setText(news.getTitle());
        Picasso.get().load(news.getImgUrl()).placeholder(R.drawable.placeholder_bg).into(holder.imageViewSearch);


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public Filter getFilter() {
        return filter;
    }
    Filter filter = new Filter() {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            ArrayList<newsModel> filterData = new ArrayList<>();
            if(constraint.toString().isEmpty()){
                filterData.addAll(backup);

            }
            else {
                for(newsModel obj:backup){
                    if(obj.getTitle().toString().toLowerCase().contains(constraint.toString().toLowerCase())){
                        filterData.add(obj);
                    }

                }
                if(filterData.isEmpty()){
                    Toast.makeText(context,"Result Not Found",Toast.LENGTH_SHORT).show();
                }

            }
            FilterResults results = new FilterResults();
            results.values=filterData;
            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            list.clear();
            list.addAll((ArrayList<newsModel>)results.values);
            notifyDataSetChanged();
        }
    };

    class SearchViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

         ImageView imageViewSearch;
         TextView textViewSearch;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);
            imageViewSearch = itemView.findViewById(R.id.imgSearch);
            textViewSearch  =itemView.findViewById(R.id.titleSearch);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            newsModel news = list.get(position);

            Intent intent = new Intent(context, DetailSearchActivity.class);
            Bundle bundle = new Bundle();
            bundle.putSerializable("prakhar",list);
            intent.putExtras(bundle);
            intent.putExtra("pos",position);
            context.startActivity(intent);


        }
    }
}
