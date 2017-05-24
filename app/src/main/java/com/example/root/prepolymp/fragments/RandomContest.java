package com.example.root.prepolymp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.root.prepolymp.Problem;
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
        if (n > cardViews.size()) {
            n = cardViews.size();
        }
        ArrayList<Integer> randNums = new ArrayList<>();
        Random random = new Random();
        while (randNums.size() != n) {
            int k = random.nextInt(problems.size());
            if (!randNums.contains(k)) {
                randNums.add(k);
            }
        }
        for (int i = cardViews.size() - 1; i >= n; --i) {
            cardViews.get(i).setVisibility(View.GONE);
        }

        ArrayList<ArrayList<TextView>> cardContent = getCardContentList(n);

        for (int i = 0; i < randNums.size(); ++i) {
            Problem problem = problems.get(randNums.get(i));
            cardContent.get(i).get(0).setText("Задача № " + problem.id);
            cardContent.get(i).get(1).setText(problem.topic + ", класс " + problem.form + ", сложность " + problem.diff);
            if (problem.text.length() > 80) {
                cardContent.get(i).get(2).setText(problem.text.substring(0, 80) + "...");
            } else {
                cardContent.get(i).get(2).setText(problem.text);
            }
            int color = 0;
            switch (problem.topic) {
                case "алгебра": color = R.color.colorAlgLight; break;
                case "комбинаторика": color = R.color.colorCombLight; break;
                case "геометрия": color = R.color.colorGeomLight; break;
            }
            cardViews.get(i).setCardBackgroundColor(ContextCompat.getColor(getActivity(), color));
        }
    }

    private ArrayList<ArrayList<TextView>> getCardContentList(int n) {
        ArrayList<ArrayList<TextView>> res = new ArrayList<>();

        ArrayList<TextView> a1 = new ArrayList<>();
        a1.add((TextView)getActivity().findViewById(R.id.cv1_tv1));
        a1.add((TextView)getActivity().findViewById(R.id.cv1_tv2));
        a1.add((TextView)getActivity().findViewById(R.id.cv1_tv3));

        ArrayList<TextView> a2 = new ArrayList<>();
        a2.add((TextView)getActivity().findViewById(R.id.cv2_tv1));
        a2.add((TextView)getActivity().findViewById(R.id.cv2_tv2));
        a2.add((TextView)getActivity().findViewById(R.id.cv2_tv3));

        ArrayList<TextView> a3 = new ArrayList<>();
        a3.add((TextView)getActivity().findViewById(R.id.cv3_tv1));
        a3.add((TextView)getActivity().findViewById(R.id.cv3_tv2));
        a3.add((TextView)getActivity().findViewById(R.id.cv3_tv3));

        ArrayList<TextView> a4 = new ArrayList<>();
        a4.add((TextView)getActivity().findViewById(R.id.cv4_tv1));
        a4.add((TextView)getActivity().findViewById(R.id.cv4_tv2));
        a4.add((TextView)getActivity().findViewById(R.id.cv4_tv3));

        ArrayList<TextView> a5 = new ArrayList<>();
        a5.add((TextView)getActivity().findViewById(R.id.cv5_tv1));
        a5.add((TextView)getActivity().findViewById(R.id.cv5_tv2));
        a5.add((TextView)getActivity().findViewById(R.id.cv5_tv3));
        
        ArrayList<TextView> a6 = new ArrayList<>();
        a6.add((TextView)getActivity().findViewById(R.id.cv6_tv1));
        a6.add((TextView)getActivity().findViewById(R.id.cv6_tv2));
        a6.add((TextView)getActivity().findViewById(R.id.cv6_tv3));

        res.add(a1);
        res.add(a2);
        res.add(a3);
        res.add(a4);
        res.add(a5);
        res.add(a6);

        return res;
    }
}
