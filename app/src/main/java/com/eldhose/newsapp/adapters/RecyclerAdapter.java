package com.eldhose.newsapp.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.R;
import com.eldhose.newsapp.room.NewsViewModel;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder> {

    private List<NewsModel> newsList;
    private Context context;
    private NewsViewModel newsViewModel;
    private OnItemClickListener mlistener;

    public RecyclerAdapter(List<NewsModel> newsList, Context context,NewsViewModel newsViewModel) {
        this.newsList = newsList;
        this.context = context;
        this.newsViewModel=newsViewModel;

    }

    public interface OnItemClickListener
    {
        void onItemClick(NewsModel newsModel);
    }

    public  void setOnItemClickListener(OnItemClickListener listener)
    {
        mlistener = listener;
    }


    @NonNull
    @Override
    public RecyclerAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view, parent, false);
        return new MyViewHolder(view,mlistener);

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MyViewHolder holder, int position) {

        NewsModel current = newsList.get(position);
        holder.titleText.setText(current.getTitle());
        holder.descText.setText(current.getDescription());
        holder.timeText.setText(current.getTime());

        Glide.with(context).asBitmap().load(current.getImageUrl()).into(holder.picView);

    }

    @Override
    public int getItemCount() {
        return newsList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titleText,descText,timeText;
        ImageView picView;
        Button addButton;
        NewsModel newsModel;


        public MyViewHolder(@NonNull View itemView, final OnItemClickListener listener) {
            super(itemView);

            titleText = itemView.findViewById(R.id.titletextView);
            picView = itemView.findViewById(R.id.picView);
            descText= itemView.findViewById(R.id.decriptiontextView);
            timeText =itemView.findViewById(R.id.timeTextView);
            addButton =itemView.findViewById(R.id.addButton);

            addButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int p =getAdapterPosition();
                    //Sets a bitmap of the image in the object
                    newsModel= newsList.get(p);
                    Toast.makeText(context, "Added to BookMark", Toast.LENGTH_SHORT).show();
                    new ImageConverter(newsViewModel).execute(newsModel);

                }
            });

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (listener != null) {
                        int position = getAdapterPosition();
                        if (position != RecyclerView.NO_POSITION) {
                            newsModel = newsList.get(position);
                            listener.onItemClick(newsModel);
                        }
                    }
                }
            });
        }
    }
}
