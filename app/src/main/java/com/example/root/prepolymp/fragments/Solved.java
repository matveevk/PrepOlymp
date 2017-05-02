package com.example.root.prepolymp.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
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

import static com.example.root.prepolymp.Storage.problems;
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

        final ArrayList<String> probText = new ArrayList<>();
        for (Problem s : problems) {
            if (s.solved) {
                String ss = "№ " + s.id + " - ";
                if (s.topic.equals("алгебра")) {
                    ss += ("<font size=3 color=#C162EA>алг</font>");
                } else if (s.topic.equals("геометрия")) {
                    ss += ("<font size=3 color=#2675BF>геом</font>");
                } else {
                    ss += ("<font size=3 color=#499351>комб</font>");
                }
                ss += "  " + s.form + " класс";
                probText.add(ss);
            }
        }

        if (probText.size() == 0) {
            tv.setVisibility(View.VISIBLE);
            tv.setText("Вы ещё ничего не решили");
        } else {
            tv.setVisibility(View.GONE);

            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                    R.layout.custom_textview, probText) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    TextView tv = (TextView)super.getView(position, convertView, parent);
                    tv.setText(Html.fromHtml(probText.get(position)));
                    return tv;
                }
            };

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
