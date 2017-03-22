package com.example.root.prepolymp;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class ProblemActivity extends Activity {

    // id, text, answer, form, difficulty, origins

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_view);
        Problem problem = new Problem(1, "Найдите наименьшее натуральное число, кратное 99, в десятичной записи которого участвуют только чётные цифры.", "228888");

        showProblem(problem);

        checkAns(problem);


    }

    public void showProblem(Problem problem) {
        TextView problemText = (TextView)findViewById(R.id.problemView);
        problemText.setText(problem.text);
        TextView problemInfo = (TextView)findViewById(R.id.problemInfo);
        problemInfo.setText(getString(R.string.problem_data, problem.id, problem.form, problem.diff));
    }

    public void checkAns(final Problem problem) {
        final EditText userAns = (EditText)findViewById(R.id.insertAnswer);
        Button checkAnsButton = (Button)findViewById(R.id.checkButton);
        checkAnsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s = userAns.getText().toString();
                TextView checkResult = (TextView)findViewById(R.id.checkResult);
                if (s.equals(problem.ans)) {
                    checkResult.setTextColor(Color.parseColor("#66BB6A"));
                    checkResult.setText("Верно!");
                } else {
                    checkResult.setTextColor(Color.RED);
                    checkResult.setText("Неверно!");
                }
            }
        });
    }
}
