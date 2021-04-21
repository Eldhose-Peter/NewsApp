package com.eldhose.newsapp.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.Glide;
import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.R;

import java.util.List;

public class RecyclerListAdapter extends RecyclerView.Adapter<RecyclerListAdapter.MyViewHolder> {

    private List<NewsModel> newsList;
    private Context context;

    public RecyclerListAdapter(List<NewsModel> newsList, Context context) {
        this.newsList = newsList;
        this.context = context;

    }

    @NonNull
    @Override
    public RecyclerListAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyler_item_view, parent, false);
        return new RecyclerListAdapter.MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerListAdapter.MyViewHolder holder, int position) {

        NewsModel current = newsList.get(position);
        holder.titleText.setText(current.getTitle());
        Glide.with(context).asBitmap().load(current.getImage()).into(holder.picView);
    }

    @Override
    public int getItemCount()  {
        return newsList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {


        TextView titleText;
        ImageView picView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleView2);
            picView = itemView.findViewById(R.id.picView2);

        }
    }
}
