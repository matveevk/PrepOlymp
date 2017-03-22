package com.example.root.prepolymp;

import java.util.ArrayList;

public class Problem {
    int id = 0;
    String text = "Текст недоступен";
    String ans = "";
    int form = 9;
    int diff = 3;
    String origins = "Неизвестно";

    Problem() {
    }

    Problem(int id, String text, String ans, int form, int diff, String origins) {
        this.id = id;
        this.text = text;
        this.ans = ans;
        this.form = form;
        this.diff = diff;
        this.origins = origins;
    }

    Problem(int id, String text, String ans, int form, int diff) {
        this.id = id;
        this.text = text;
        this.ans = ans;
        this.form = form;
        this.diff = diff;
    }

    Problem(int id, String text, String ans) {
        this.id = id;
        this.text = text;
        this.ans = ans;
    }

}
