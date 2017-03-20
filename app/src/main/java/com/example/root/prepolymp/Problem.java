package com.example.root.prepolymp;

import java.util.ArrayList;

public class Problem {
    String text;
    String ans;
    int form;
    String origins;

    Problem() {
        text = "";
        ans = "";
        form = 9;
        origins = "Неизвестно";
    }

    Problem(String text, String ans, int form) {
        this.text = text;
        this.ans = ans;
        this.form = form;
    }

}
