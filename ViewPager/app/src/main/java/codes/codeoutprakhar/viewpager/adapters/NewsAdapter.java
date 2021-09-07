package codes.codeoutprakhar.viewpager.adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import codes.codeoutprakhar.viewpager.R;
import codes.codeoutprakhar.viewpager.newsModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.DataViewHolder>{

    ArrayList<newsModel> list;
    Context context;

    public NewsAdapter(ArrayList<newsModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public DataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_one,parent,false);
        return new DataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DataViewHolder holder, int position) {
          newsModel newsModel = list.get(position);
          holder.txtTitle.setText(newsModel.getTitle());
          holder.txtDescription.setText(newsModel.getDescription());
          holder.txtMoreDetails.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String url = newsModel.getSourceUrl();
                  Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                  context.startActivity(Intent.createChooser(intent, "Browse with"));
              }
          });
          holder.share.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  String title = newsModel.getTitle();
                  String des = newsModel.getDescription();
                  String link = newsModel.getSourceUrl();
                  Intent intent = new Intent(Intent.ACTION_SEND);
                  intent.setType("text/plain");
                  intent.putExtra(Intent.EXTRA_TEXT,"Title: "+title+ "\n"+ "Description: "+des+"\n"+"Url: "+link);
                  context.startActivity(Intent.createChooser(intent, "Share..."));
              }
          });

        Picasso.get().load(newsModel.getImgUrl()).placeholder(R.drawable.placeholder_bg).into(holder.imageView);




    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class DataViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView,share;
        TextView txtTitle,txtDescription,txtMoreDetails;
        public DataViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imgView);
            txtTitle = itemView.findViewById(R.id.txtTitle);
            txtDescription = itemView.findViewById(R.id.txtDiscrip);
            txtMoreDetails = itemView.findViewById(R.id.moreDetails);
            share =  itemView.findViewById(R.id.sharei);
        }
    }
}
