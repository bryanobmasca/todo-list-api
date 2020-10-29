package com.oocl.todolistapi.dto;

public class TodoRequest {
    private String text;
    private boolean done;

    public TodoRequest() {
    }

    public TodoRequest(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return done;
    }
}
