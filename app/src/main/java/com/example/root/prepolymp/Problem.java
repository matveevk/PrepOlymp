package com.example.root.prepolymp;

public class Problem {
    public int id = 0;
    public String text = "Текст недоступен";
    public String ans = "";
    public String topic = "Не указано";
    public int form = 9;
    public int diff = 3;
    public String origins = "Неизвестно";

    Problem() {
    }

    Problem(int id, String text, String ans, String topic, int form, int diff, String origins) {
        this.id = id;
        this.text = text;
        this.ans = ans;
        this.topic = topic;
        this.form = form;
        this.diff = diff;
        this.origins = origins;
    }

    Problem(int id, String text, String ans, String topic, int form, int diff) {
        this.id = id;
        this.text = text;
        this.ans = ans;
        this.topic = topic;
        this.form = form;
        this.diff = diff;
    }

    Problem(int id, String text, String ans, String topic, int form) {
        this.id = id;
        this.text = text;
        this.ans = ans;
        this.topic = topic;
        this.form = form;
    }

    Problem(int id, String text, String ans, String topic) {
        this.id = id;
        this.text = text;
        this.topic = topic;
        this.ans = ans;
    }

    @Override
    public String toString() {
        return this.text;
    }
}
