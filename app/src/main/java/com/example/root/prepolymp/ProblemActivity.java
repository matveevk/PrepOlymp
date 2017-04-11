package com.example.root.prepolymp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.root.prepolymp.fragments.ProblemList;
import com.example.root.prepolymp.fragments.Stats;

import static com.example.root.prepolymp.Start.isFavourite;
import static com.example.root.prepolymp.Start.isLater;
import static com.example.root.prepolymp.Start.isSolved;

public class ProblemActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE = "com.example.root.prepolymp.MESSAGE";
    Problem problem = new Problem(0, "Нет условия", "", "алгебра");

    // id, text, answer, form, difficulty, origins

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_view);

        Intent intent = getIntent();
        int i = intent.getIntExtra(ProblemList.EXTRA, -1);

        problem = Start.problems.get(i);

        setTitle("Задача № " + problem.id);

        /*
        // stretching the linearLayout to what we need (and problemText)
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutProblemInfo);
        int width = getResources().getDisplayMetrics().widthPixels / 10 * 9;
        int height = linearLayout.getHeight();
        linearLayout.setLayoutParams(new LinearLayout.LayoutParams(width, height));
        */
            // OR
        /*
        // another option
        LinearLayout linearLayout = (LinearLayout)findViewById(R.id.linearLayoutProblemInfo);
        linearLayout.setMinimumWidth(screenWidth() / 10 * 9);
        TextView tv = (TextView)findViewById(R.id.problemText);
        tv.setWidth(screenWidth() / 10 * 9);
        */

        showProblem(problem);

        if (isSolved.get(problem.id - 1)) {
            TextView tv = (TextView)findViewById(R.id.checkResult);
            tv.setText("Решено");
            tv.setTypeface(null, Typeface.BOLD);
            tv.setTextColor(ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary));
            EditText editText = (EditText)findViewById(R.id.insertAnswer);
            editText.setText(problem.ans);
        }

        openInfo(problem);

        checkAns(problem);
    }

    public void showProblem(Problem problem) {
        TextView problemText = (TextView)findViewById(R.id.problemText);
        problemText.setText(problem.text);
        TextView problemInfo = (TextView)findViewById(R.id.problemInfo);
        problemInfo.setText(getString(R.string.problem_data, problem.id, problem.form, problem.diff));
    }

    public void openInfo(final Problem problem) {
        ImageButton infoImageButton = (ImageButton)findViewById(R.id.infoImageButton);
        infoImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(view.getContext(), ProblemInfo.class);
                intent.putExtra(EXTRA_MESSAGE, problem.id);
                view.getContext().startActivity(intent);
            }
        });
    }

    public void checkAns(final Problem problem) {
        final EditText userAns = (EditText)findViewById(R.id.insertAnswer);
        Button checkAnsButton = (Button)findViewById(R.id.checkButton);
        checkAnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = userAns.getText().toString();
                TextView checkResult = (TextView)findViewById(R.id.checkResult);
                addAtt(problem.topic);
                if (s.equals(problem.ans)) {
                    checkResult.setTextColor(Color.parseColor("#66BB6A"));
                    checkResult.setText("Верно!");
                    isSolved.set(problem.id - 1, true);
                    addCor(problem.topic);
                } else {
                    checkResult.setTextColor(Color.RED);
                    checkResult.setText("Неверно!");
                }
            }
        });
    }

    public void addAtt(String s) {
        switch (s) {
            case "алгебра": ++Stats.att_alg; break;
            case "комбинаторика": ++Stats.att_comb; break;
            case "геометрия": ++Stats.att_geom; break;
        }
    }

    public void addCor(String s) {
        switch (s) {
            case "алгебра": ++Stats.cor_alg; break;
            case "комбинаторика": ++Stats.cor_comb; break;
            case "геометрия": ++Stats.cor_geom; break;
        }
    }

    public int screenWidth() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        return dm.widthPixels;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_problem_activity, menu);

        if (isFavourite.get(problem.id - 1)) {
            menu.findItem(R.id.add_to_favourites).setIcon(R.drawable.ic_menu_favourites_true);
        } else {
            menu.findItem(R.id.add_to_favourites).setIcon(R.drawable.ic_menu_favourites_false);
        }

        if (isLater.get(problem.id - 1)) {
            menu.findItem(R.id.add_to_later).setIcon(R.drawable.ic_add_to_later_true);
        } else {
            menu.findItem(R.id.add_to_later).setIcon(R.drawable.ic_add_to_later_false);
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {

            case R.id.add_to_favourites:
                // TODO: 4/10/17 change the Favourites listview
                if (isFavourite.get(problem.id - 1)) {
                    isFavourite.set(problem.id - 1, false);
                    item.setIcon(R.drawable.ic_menu_favourites_false);
                    Toast.makeText(this, "Удалено из понравившихся", Toast.LENGTH_LONG).show();
                } else {
                    isFavourite.set(problem.id - 1, true);
                    item.setIcon(R.drawable.ic_menu_favourites_true);
                    Toast.makeText(this, "Добавлено в понравившиеся", Toast.LENGTH_LONG).show();
                }
                break;

            case R.id.add_to_later:
                // TODO: 4/10/17 change the Favourites listview
                if (isLater.get(problem.id - 1)) {
                    isLater.set(problem.id - 1, false);
                    item.setIcon(R.drawable.ic_add_to_later_false);
                    Toast.makeText(this, "Удалено из отложенных", Toast.LENGTH_LONG).show();
                } else {
                    isLater.set(problem.id - 1, true);
                    item.setIcon(R.drawable.ic_add_to_later_true);
                    Toast.makeText(this, "Добавлено в отложенные", Toast.LENGTH_LONG).show();
                }
                break;

            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
