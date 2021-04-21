package com.eldhose.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.R;
import com.eldhose.newsapp.adapters.RecyclerListAdapter;
import com.eldhose.newsapp.room.NewsViewModel;

import java.util.List;

public class BookMarkFragment extends Fragment {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    NewsViewModel newsViewModel;
    RecyclerView recyclerView;

    public BookMarkFragment() {
        // Required empty public constructor
    }

    public static BookMarkFragment newInstance(String param1, String param2) {
        BookMarkFragment fragment = new BookMarkFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

        newsViewModel = new NewsViewModel(getActivity().getApplication());
        newsViewModel.getBookMarks().observe(getActivity(), new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                RecyclerListAdapter recyclerAdapter = new RecyclerListAdapter(newsModels,getContext());
                recyclerView.setAdapter(recyclerAdapter);
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_book_mark, container, false);

        recyclerView = view.findViewById(R.id.recyclerListView);
        return view;
    }
}
