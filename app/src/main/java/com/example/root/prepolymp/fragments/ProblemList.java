package com.example.root.prepolymp.fragments;

import android.app.ListActivity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import static com.example.root.prepolymp.MainActivity.problems;

public class ProblemList extends Fragment {
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
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), R.layout.custom_textview, probText);
        listView.setAdapter(arrayAdapter);
    }
}
