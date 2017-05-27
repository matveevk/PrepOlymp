package com.example.root.prepolymp.fragments;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Html;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.prepolymp.Problem;
import com.example.root.prepolymp.ProblemActivity;
import com.example.root.prepolymp.R;

import java.util.ArrayList;

import static com.example.root.prepolymp.Storage.problems;
import static com.example.root.prepolymp.fragments.ProblemList.EXTRA;

public class Favourites extends Fragment {

    public static ArrayAdapter<String> adapterFavourites;

    int longClickPos = 0;

    ArrayList<String> probText = null;
    ArrayList<Integer> probNums = null;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.menu_favourites, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Понравившиеся");
        ListView listView = (ListView) view.findViewById(R.id.list_favourites);
        TextView tv = (TextView) view.findViewById(R.id.favourites_no_problems);

        probText = new ArrayList<>();
        probNums = new ArrayList<>();
        for (Problem s : problems) {
            if (s.liked) {
                probNums.add(s.id - 1);
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
            tv.setText("Нет понравившихся задач");
        } else {
            tv.setVisibility(View.GONE);

            adapterFavourites = new ArrayAdapter<String>(getActivity().getApplicationContext(),
                                                            R.layout.custom_textview, probText) {
                @NonNull
                @Override
                public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
                    TextView tv = (TextView)super.getView(position, convertView, parent);
                    if (problems.get(probNums.get(position)).solved) {
                        tv.setText(Html.fromHtml(probText.get(position)) + " (решено)");
                        tv.setTextColor(Color.GRAY);
                    } else {
                        tv.setTextColor(Color.BLACK);
                        tv.setText(Html.fromHtml(probText.get(position)));
                    }
                    return tv;
                }
            };
            listView.setAdapter(adapterFavourites);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                    Intent intent = new Intent(getActivity(), ProblemActivity.class);
                    // TODO: 5/3/17 if change probText change following line
                    intent.putExtra(EXTRA, probNums.get(i));
                    getActivity().startActivity(intent);
                }
            });

            registerForContextMenu(listView);

            listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    longClickPos = probNums.get(position);
                    return false;
                }
            });
        }
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
        Problem problem = problems.get(longClickPos);
        Log.d("id", "" + problem.id);
        switch(item.getItemId()) {
            case R.id.menu_problem_list_like_00: problem.liked = true; Toast.makeText(getActivity(), "Добавлено в понравившиеся", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_problem_list_like_01: problem.liked = true; Toast.makeText(getActivity(), "Добавлено в понравившиеся", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_problem_list_like_10: problem.liked = false; probText.remove(info.position); probNums.remove(info.position); Toast.makeText(getActivity(), "Удалено из понравившихся", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_problem_list_like_11: problem.liked = false; probText.remove(info.position); probNums.remove(info.position); Toast.makeText(getActivity(), "Удалено из понравившихся", Toast.LENGTH_LONG).show(); break;

            case R.id.menu_problem_list_later_00: if (problem.solved) Toast.makeText(getActivity(), "Нельзя отложить решённую задачу", Toast.LENGTH_LONG).show(); else {problem.marked = true; Toast.makeText(getActivity(), "Отложено", Toast.LENGTH_SHORT).show();} break;
            case R.id.menu_problem_list_later_01: problem.marked = false; Toast.makeText(getActivity(), "Убрано из отложенных", Toast.LENGTH_LONG).show(); break;
            case R.id.menu_problem_list_later_10: if (problem.solved) Toast.makeText(getActivity(), "Нельзя отложить решённую задачу", Toast.LENGTH_LONG).show(); else {problem.marked = true; Toast.makeText(getActivity(), "Отложено", Toast.LENGTH_SHORT).show();} break;
            case R.id.menu_problem_list_later_11: problem.marked = false; Toast.makeText(getActivity(), "Убрано из отложенных", Toast.LENGTH_LONG).show(); break;
        }
        adapterFavourites.notifyDataSetChanged();
        return super.onContextItemSelected(item);
    }
}
