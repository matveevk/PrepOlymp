package com.example.root.prepolymp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.problem_view);
        Problem problem = new Problem("Найдите a + b, если a = 2, b = 5.", "7", 1);
        showProblem(problem);
    }

    public void showProblem(Problem problem) {
        TextView problemText = (TextView)findViewById(R.id.problemView);
        problemText.setText(problem.text);
        int problemNo = 0;
        TextView problemInfo = (TextView)findViewById(R.id.problemInfo);
        problemInfo.setText(getString(R.string.problem_number, problemNo));

    }
}
