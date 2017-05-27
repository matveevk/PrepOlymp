package com.example.root.prepolymp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.ProblemActivity;
import com.example.root.prepolymp.R;

import java.util.ArrayList;

import static com.example.root.prepolymp.Storage.problems;

public class ProblemList extends Fragment {

    public static final String EXTRA = "ProblemList";
    public static ArrayAdapter<String> adapterAllProblems;

    int longClickPos = 0;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_problem_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Задачи");

        final ArrayList<String> probText = new ArrayList<>();
        for (Problem s : problems) {
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

        Button button = (Button) getActivity().findViewById(R.id.applyGoToNum);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = (EditText)getActivity().findViewById(R.id.insertGoToNum);
                String s = editText.getText().toString();
                int index = Integer.parseInt(s) - 1;
                if (index < 0 || index >= problems.size()) {
                    Toast.makeText(getContext(), "Такой задачи нет", Toast.LENGTH_LONG).show();
                } else {
                    Intent intent = new Intent(getActivity(), ProblemActivity.class);
                    intent.putExtra(EXTRA, index);
                    getActivity().startActivity(intent);
                }
            }
        });

        ListView listView = (ListView)view.findViewById(R.id.list_problems);
        adapterAllProblems = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                    R.layout.custom_textview, probText) {
            @NonNull
            @Override
            public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                TextView tv = (TextView)super.getView(position, convertView, parent);
                if (problems.get(position).solved) {
                    tv.setText(Html.fromHtml(probText.get(position)) + " (решено)");
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                    tv.setText(Html.fromHtml(probText.get(position)));
                }
                return tv;
            }
        };

        listView.setAdapter(adapterAllProblems);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(), ProblemActivity.class);
                intent.putExtra(EXTRA, i);
                getActivity().startActivity(intent);
            }
        });

        registerForContextMenu(listView);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                longClickPos = position;
                return false;
            }
        });
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater inflater = getActivity().getMenuInflater();
        Problem problem = problems.get(longClickPos);
        boolean liked = problem.liked, latered = problem.marked;
        if (liked && latered) {
            inflater.inflate(R.menu.menu_problem_list_11, menu);
        } else if (liked) {
            inflater.inflate(R.menu.menu_problem_list_10, menu);
        } else if (latered) {
            inflater.inflate(R.menu.menu_problem_list_01, menu);
        } else {
            inflater.inflate(R.menu.menu_problem_list_00, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Problem problem = problems.get(info.position);
        switch(item.getItemId()) {
            case R.id.menu_problem_list_like_00: problem.liked = true; break;
            case R.id.menu_problem_list_like_01: problem.liked = true; break;
            case R.id.menu_problem_list_like_10: problem.liked = false; break;
            case R.id.menu_problem_list_like_11: problem.liked = false; break;

            case R.id.menu_problem_list_later_00: problem.marked = true; break;
            case R.id.menu_problem_list_later_01: problem.marked = false; break;
            case R.id.menu_problem_list_later_10: problem.marked = true; break;
            case R.id.menu_problem_list_later_11: problem.marked = false; break;
        }
        return super.onContextItemSelected(item);
    }
}
