package com.example.root.prepolymp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.root.prepolymp.R;

import java.util.ArrayList;
import java.util.Random;

import static com.example.root.prepolymp.Storage.problems;

public class RandomContest extends Fragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_random_contest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Порешать");

        getContest(5);
    }

    private void getContest(int n) {
        ArrayList<CardView> cardViews = new ArrayList<>();
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView1));
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView2));
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView3));
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView4));
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView5));
        cardViews.add((CardView)getActivity().findViewById(R.id.problemCardView6));

        ArrayList<Integer> randNums = new ArrayList<>();
        Random random = new Random();
        while (randNums.size() != n) {
            int k = random.nextInt(problems.size()) + 1;
            if (!randNums.contains(k)) {
                randNums.add(k);
            }
        }


    }
}
