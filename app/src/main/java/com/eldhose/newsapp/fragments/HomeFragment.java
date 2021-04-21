package com.eldhose.newsapp.fragments;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.navigation.Navigation;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eldhose.newsapp.NewsModel;
import com.eldhose.newsapp.R;
import com.eldhose.newsapp.adapters.RecyclerAdapter;
import com.eldhose.newsapp.room.NewsViewModel;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HomeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HomeFragment extends Fragment {

    private static final String ARG_PARAM1 = "news";
    private String mParam1;

    NewsViewModel viewModel;
    ViewPager2 viewPager2;
    SwipeRefreshLayout swipeRefreshLayout;
    RecyclerAdapter recyclerAdapter;
    View view;

    public HomeFragment() {
        // Required empty public constructor
    }

    public static HomeFragment newInstance(NewsModel param1) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1,param1);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
        }







    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home, container, false);

        viewPager2 = view.findViewById(R.id.viewPager);
        swipeRefreshLayout =view.findViewById(R.id.swipe_to_refresh);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                viewModel.getTopHeadlines().observe(getActivity(), new Observer<List<NewsModel>>() {
                    @Override
                    public void onChanged(List<NewsModel> newsModels) {
                        RecyclerAdapter recyclerAdapter = new RecyclerAdapter(newsModels,getContext(),viewModel);
                        viewPager2.setAdapter(recyclerAdapter);
                    }
                });

                swipeRefreshLayout.setRefreshing(false);

            }
        });

        viewModel = new NewsViewModel(getActivity().getApplication());
        viewModel.getTopHeadlines().observe(getActivity(), new Observer<List<NewsModel>>() {
            @Override
            public void onChanged(List<NewsModel> newsModels) {
                recyclerAdapter = new RecyclerAdapter(newsModels,getContext(),viewModel);
                viewPager2.setAdapter(recyclerAdapter);

                recyclerAdapter.setOnItemClickListener(new RecyclerAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(NewsModel newsModel) {
                        //open details fragment from here

                        Bundle bundle = new Bundle();
                        bundle.putSerializable("news",newsModel);
                        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailsFragment,bundle);


                    }
                });

            }
        });


        return view;
    }
}
