package com.example.root.prepolymp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.util.Pair;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.ProblemActivity;
import com.example.root.prepolymp.R;

import java.util.ArrayList;
import java.util.Random;

import static com.example.root.prepolymp.Storage.problems;

public class RandomContest extends Fragment {

    public static final String RANDOM_CONTEST_INTENT = "RANDOM_CONTEST_INTENT";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_random_contest, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Контест");

        Spinner spinner = (Spinner)getActivity().findViewById(R.id.random_contest_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(), R.array.random_contest_array, R.layout.spinner_random_contest);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        Button button = (Button)getActivity().findViewById(R.id.contest_gen_btn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Spinner spinner = (Spinner)getActivity().findViewById(R.id.random_contest_spinner);
                int n = spinner.getSelectedItem().toString().charAt(0) - '0';
                Log.d("bbbbbbb", spinner.getSelectedItem().toString());
                getContest(n);
            }
        });

        getContest(5);
    }

    private void getContest(int n) {
        int maxSize = 6;

        ArrayList<Pair<CardView, Integer>> cardViewsHelper = new ArrayList<>(), cardViews = new ArrayList<>();
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView1), 0));
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView2), 0));
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView3), 0));
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView4), 0));
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView5), 0));
        cardViewsHelper.add(Pair.create((CardView)getActivity().findViewById(R.id.problemCardView6), 0));

        if (n > maxSize) {
            n = maxSize;
        }
        ArrayList<Integer> randNums = new ArrayList<>();
        Random random = new Random();
        while (randNums.size() != n) {
            int k = random.nextInt(problems.size());
            if (!randNums.contains(k)) {
                cardViews.add(Pair.create(cardViewsHelper.get(randNums.size()).first, k));
                randNums.add(k);
            }
        }

        ArrayList<ArrayList<TextView>> cardContent = getCardContentList(maxSize);

        for (int i = 0; i < maxSize; ++i) {
            Problem problem = problems.get(randNums.get(i < n ? i : 0));
            cardContent.get(i).get(0).setText("Задача № " + problem.id);
            switch (problem.topic) {
                case "алгебра": cardContent.get(i).get(0).setTextColor(getActivity().getResources().getColor(R.color.colorAlgRandomContest)); break;
                case "комбинаторика": cardContent.get(i).get(0).setTextColor(getActivity().getResources().getColor(R.color.colorComb)); break;
                case "геометрия": cardContent.get(i).get(0).setTextColor(getActivity().getResources().getColor(R.color.colorGeom)); break;
            }

            cardContent.get(i).get(1).setText(problem.topic + ", класс " + problem.form + ", сложность " + problem.diff);

            if (problem.text.length() > 80) {
                cardContent.get(i).get(2).setText(problem.text.substring(0, 80) + "...");
            } else {
                cardContent.get(i).get(2).setText(problem.text);
            }

            int color = android.R.color.white;
            switch (problem.topic) {
                case "алгебра": color = R.color.colorAlgLight; break;
                case "комбинаторика": color = R.color.colorCombLight; break;
                case "геометрия": color = R.color.colorGeomLight; break;
            }
            cardViewsHelper.get(i).first.setCardBackgroundColor(getActivity().getResources().getColor(color));
        }

        for (int i = maxSize - 1; i >= n; --i) {
            cardViewsHelper.get(i).first.setVisibility(View.GONE);
            for (TextView tv : cardContent.get(i)) {
                tv.setVisibility(View.GONE);
            }
        }

        for (int i = 0; i < n; ++i) {
            cardViewsHelper.get(i).first.setVisibility(View.VISIBLE);
            for (TextView tv : cardContent.get(i)) {
                tv.setVisibility(View.VISIBLE);
            }
        }

        for (int i = 0; i < cardViews.size(); ++i) {
            final int j = i;
            final int problemNo = cardViews.get(j).second;
            cardViews.get(i).first.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), ProblemActivity.class);
                    intent.putExtra(RANDOM_CONTEST_INTENT, problemNo);
                    getActivity().startActivity(intent);
                }
            });
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
