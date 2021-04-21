package com.eldhose.newsapp.fragments;

import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.graphics.drawable.DrawableWrapper;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.eldhose.newsapp.MainActivity;
import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.R;
import com.google.android.material.appbar.CollapsingToolbarLayout;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */

public class DetailsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private NewsModel newsModel;
    CollapsingToolbarLayout collapsingToolbarLayout;
    TextView contentTextView;
    ImageView imageView ;


    public DetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetailsFragment newInstance(String param1) {
        DetailsFragment fragment = new DetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

            newsModel = (NewsModel) getArguments().getSerializable("news");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_details, container, false);

        collapsingToolbarLayout = view.findViewById(R.id.collapsingToolbar);
        contentTextView = view.findViewById(R.id.contentTextView);
        imageView = view.findViewById(R.id.imgView);

        Glide.with(getContext()).asDrawable().load(newsModel.getImageUrl()).into(imageView);
        contentTextView.setText(newsModel.getContent());
        collapsingToolbarLayout.setTitle(newsModel.getTitle());



        return view;
    }
}
