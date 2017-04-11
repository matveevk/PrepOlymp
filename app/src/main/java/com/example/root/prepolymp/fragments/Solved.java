package com.example.root.prepolymp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.ProblemActivity;
import com.example.root.prepolymp.R;

import java.util.ArrayList;

import static com.example.root.prepolymp.Start.isSolved;
import static com.example.root.prepolymp.Start.problems;
import static com.example.root.prepolymp.fragments.ProblemList.EXTRA;

public class Solved extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_solved, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Решённые");
        ListView listView = (ListView) view.findViewById(R.id.list_solved);
        TextView tv = (TextView) view.findViewById(R.id.solved_no_problems);

        ArrayList<String> probText = new ArrayList<>();
        for (int i = 0; i < isSolved.size(); ++i) {
            if (isSolved.get(i) == true) {
                Problem problem = problems.get(i);
                probText.add("Задача № " + problem.id);
            }
        }

        if (probText.size() == 0) {
            tv.setVisibility(View.VISIBLE);
            tv.setText("Вы пока ничего не решили");
        } else {
            tv.setVisibility(View.GONE);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity().getApplicationContext(),
                    R.layout.custom_textview, probText);
            listView.setAdapter(arrayAdapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), ProblemActivity.class);
                    intent.putExtra(EXTRA, i);
                    getActivity().startActivity(intent);
                }
            });
        }
    }

}
