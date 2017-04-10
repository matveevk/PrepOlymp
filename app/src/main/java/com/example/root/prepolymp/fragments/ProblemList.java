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

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.ProblemActivity;
import com.example.root.prepolymp.R;

import java.util.ArrayList;

import static com.example.root.prepolymp.Start.problems;

public class ProblemList extends Fragment {

    public static final String EXTRA = "ProblemList";

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_problem_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Задачи");

        ArrayList<String> probText = new ArrayList<>();
        for (Problem s : problems) {
            probText.add("Задача № " + s.id);
        }

        ListView listView = (ListView)view.findViewById(R.id.list_problems);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
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
